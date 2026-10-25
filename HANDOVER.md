# 📘 CELESTIUM 项目记忆与交接手册

> **最后更新时间**：2026-10-25 21:35  
> **更新概要**：记录第三套木材「析木（ximu）」全套链路构建与落地；全面闭环全木材衍生建材（楼梯、台阶及其双层掉落、合成配方与标签）；深度排查并攻克 1.21.11 树木世界生成（Worldgen）、CherryTrunkPlacer 源码上限约束、树冠悬空 Bug、树叶 7 格衰败铁律与 PlacedFeature 离散权重/稀有度参数标准；创造栏已完整扩容至 21 个方块物品。

---

## 1. 项目中特殊要求

- **用户开发规范与偏好**：
  - **严禁擅自修改用户代码**：始终以诊断、架构指导、原理解释和就绪代码块为先，由用户主导代码改动。
  - **生成新代码前必须先读参考代码**：生成新树或新功能代码前，必须先完整查阅当前项目已有的标杆源码（如 `CelestiumRedSandalBlocks.java`），严格模仿用户的排版格式、缩进与命名习惯。
  - **严格杜绝自以为是的冗长注释**：严禁在代码中堆砌多余的 AI 教学或解释性长注释，仅保留类似 `// <tree_id>_log` 这样纯粹极简的小节标头注释。**一切以用户的现有代码为最高标准！**
  - **零容错原则**：生成完毕必须保证 Gradle 编译零语法报错、JSON 格式完全合规。
  - **美术与资源管理偏好**：3D 方块/物品模型优先在 Blockbench 中手工雕琢和贴图；数据驱动配置（Loot Table、Tags、Worldgen）可灵活手写或由 DataGen 批量维护。
  - **语言翻译规范**：树木生成器及模组常规维护中，**仅维护英文语言文件（`assets/<modid>/lang/en_us.json`）**，根据给定的英文名称自动配置翻译，**严禁自动生成或修改中文翻译（`zh_cn.json`）**。

- **环境与构建链限制**：
  - **JDK 版本要求**：构建工具链使用 `fabric-loom:1.18-SNAPSHOT`，必须锁定 **Java 25+**（Adoptium Temurin `jdk-25.0.4.1+1`）。终端执行 Gradle 命令时需注意 `$env:JAVA_HOME`。
  - **VS Code 设置项**：Windows 绝对路径切勿包裹外层双引号 `"`。
  - **缩进规范**：严格采用 **4 个空格**。

- **核心机制与开发避坑指南（Minecraft 1.21.11）**：
  - **模型继承与面剔除铁律（防帧率暴跌）**：
    - 原木（竖直/水平）、木板、树叶、台阶、楼梯模型**必须严格继承原版父模型（`cube_column`、`cube_column_horizontal`、`cube_all`、`slab`、`slab_top`、`stairs`、`inner_stairs`、`outer_stairs`）**。
    - **严禁**使用缺少 `cullface` 的 Blockbench 手工 elements 散装拼凑完整方块，否则成群树木内部将反复渲染数万无效面，导致玩家 FPS 灾难性暴跌。
  - **树根泥土提供者规范（dirt_provider）**：
    - 在 `configured_feature/<tree_id>_tree.json` 中，`dirt_provider` 的方块名称必须配置为天界泥土（`<modid>:celestial_dirt`），**严禁使用原版 `minecraft:dirt`**，确保树木生成时树根底部的泥土完美融入天界地貌。
  - **全套衍生建材标配（楼梯与台阶）**：
    - 每套新树木必须完整产出台阶（`<tree_id>_slab`）与楼梯（`<tree_id>_stairs`）。
    - 台阶必须使用 `SlabBlock`，楼梯必须使用 `StairBlock`。
    - 掉落物表：台阶必须带 `type=double` 判断（单台阶掉 1 个，双层拼合掉 2 个）。
    - 合成表：3 木板合 6 台阶（横排），6 木板合 4 楼梯（阶梯状）。
    - 标签绑定：必须同时加入 `minecraft:tags/block/wooden_stairs.json`、`minecraft:tags/item/wooden_stairs.json`、`minecraft:tags/block/wooden_slabs.json`、`minecraft:tags/item/wooden_slabs.json`，确保斧头加速与木质配方兼容。
  - **树叶 7 格衰败距离（Leaves Decay Distance）铁律**：
    - 原版 `LeavesBlock` 的 `distance` 属性上限被硬编码为 **7**。
    - 任何树叶只要距离最近原木的曼哈顿距离超过 7 格，原版就会无情判定其失去支撑并迅速**凋零自毁**。
    - **避坑**：树冠半径（`radius`）绝不能无限制开大（建议控制在 `4 ~ 5` 以内），若需要更庞大的树冠，必须通过横向伸长分枝原木（`branch_horizontal_length`）把原木支撑点送入树叶深处，而不是单纯调大叶球半径。
    - **新原木必须立即进标签**：新原木必须即刻加入 `data/minecraft/tags/block/logs.json`，否则整棵树在生成后 10 秒内树叶全部掉光。
  - **树木生成形态与 Codec 源码硬编码避坑**：
    - **`cherry_trunk_placer` 源码限制**：Mojang 底层对樱花树干生成器进行了硬编码校验（`IntProvider.codec(1, 3)`）。`branch_count`（分枝数量）的最大值被锁死为 **3**，`branch_horizontal_length` 被锁死为 **4**。若填写大于上限的数值，游戏会在数据包加载阶段抛出 `Value provider too high: 3` 崩溃拒绝启动。
    - **树冠悬空 Bug 根源**：`fancy_foliage_placer` 或部分叶片生成器若设置了 `"offset": 4`，会将叶冠生成原点向上拔高 4 格，导致树枝末端与树叶之间出现 2~3 层尴尬的空气断层。将 `"offset"` 设为 `0 ~ 2` 即可使树叶紧密贴合包裹树枝。
    - **树叶包裹主干过多问题**：樱花树冠是以分枝末端向下生成 `height` 层叶子。若觉得主干被包裹太多，可适当减小 `height`（如从 5 降至 3~4）或将 `offset` 提高为 `1`。
  - **PlacedFeature 放置规则避坑**：
    - **`rarity_filter`**：参数名称为 **`"chance"`**（如 `"chance": 10` 表示平均每 10 个区块生成 1 次），**严禁写成 `"count"`**。
    - **离散概率与暴点树群**：若希望实现“要么 0 棵，要么单区块爆发 5 棵”，必须使用 `"type": "minecraft:count"` 嵌套 `"type": "minecraft:weighted_list"`，通过 `distribution: [{data, weight}]` 分配离散权重。
  - **方块物品语言前缀规范**：
    - 注册 `BlockItem` 时必须调用 `new Item.Properties().setId(itemKey).useBlockDescriptionPrefix()`，保证翻译键严格统一为 `block.<modid>.<name>`。

---

## 2. 项目总体规划和设计

- **模组名称与定位**：
  - **名称**：`CELESTIUM`（中文主题：中式天界 / 仙霄 / 浮空仙山）。
  - **Mod ID**：`celestium`。
  - **技术栈**：Minecraft 1.21.11、Fabric Loader 0.19.5、Mojang Official Mappings (Mojmap)、Fabric Loom 1.18.2。

- **核心架构：分层模块化设计**：
  1. **注册层（Registry）**：
     - `registry/CelestiumBlocks.java`：方块总入口，聚合 Flora、Wood 等子线。
     - `registry/block/CelestiumFloraBlocks.java`：草本与基础地表（`celestial_grass_block`、`celestial_dirt`、`celestial_grass`、`celestial_stone`）。
     - `registry/block/CelestiumWoodBlocks.java`：木质方块总线，挂载各树种的 `.initialize()`。
     - `registry/block/wood/CelestiumRedSandalBlocks.java`：降真香/红檀木全套（原木、树叶、树苗、木板、台阶、楼梯）。
     - `registry/block/wood/CelestiumSandalBlocks.java`：白檀木全套（原木、树叶、树苗、木板、台阶、楼梯）。
     - `registry/block/wood/CelestiumXimuBlocks.java`：析木全套（原木、落叶树叶、树苗、木板、台阶、楼梯）。
     - `registry/worldgenerator/CelestiumTreeFeatures.java`：树木 Feature Key 定义（`RED_SANDAL_TREE`、`SANDAL_TREE`、`XIMU_TREE`）。
     - `registry/particle/CelestiumParticles.java`：落叶粒子注册（`RED_SANDAL_LEAVES`、`SANDAL_LEAVES`、`XIMU_LEAVES`）。
     - `registry/CelestiumItemGroups.java`：创造栏总线，模组专属标签页 `celestium_tab`。
  2. **客户端渲染层（Client）**：
     - `client/CelestiumClient.java`：统一管理 `ChunkSectionLayer.CUTOUT` 半透明渲染图层。
     - `client/CelestiumParticleFactories.java`：统一绑定飘落叶粒子工厂（`FallingLeavesParticle.CherryProvider::new`）。
  3. **世界生成与特征层（Worldgen）**：
     - `src/main/resources/data/celestium/worldgen/configured_feature/`：
       - `red_sandal_tree.json`、`sandal_tree.json`、`ximu_tree.json`（东方仙木形态配置，支持 `celestial_dirt` 根土）。
     - `src/main/resources/data/celestium/worldgen/placed_feature/`：
       - `patch_celestial_grass_placed.json`（草地斑块生成）。
       - `red_sandal_trees_placed.json`（稀有度放置 `chance: 2`）。
       - `sandal_trees_placed.json`（权重离散数量放置）。
       - `ximu_trees_placed.json`（稀有神木放置 `chance: 10`）。
     - `src/main/resources/data/celestium/worldgen/biome/celestial_plains.json`：
       - 苍穹平原生物群系环境参数、流金色水体（`#F6C85F`）与第 10 阶段植被装饰（`VEGETAL_DECORATION`）特征挂载。

- **资产与数据目录架构**：
  ```text
  src/main/resources/
  ├── assets/celestium/
  │   ├── blockstates/                     # 方块状态定义 (柱状轴向、台阶3态、楼梯40项朝向)
  │   ├── items/                           # 1.21+ 规范物品定义 (model 路由)
  │   ├── lang/en_us.json                  # 官方英文国际化语言文件
  │   ├── models/
  │   │   ├── block/                       # 严格继承原版 cube_column, cube_all, slab, stairs
  │   │   └── item/                        # 树苗 2D 平整手持模型 (item/generated)
  │   ├── particles/                       # 树叶飘落粒子定义
  │   └── textures/
  │       ├── block/                       # 16x16 方块像素贴图
  │       └── particle/                    # 粒子贴图 (red_sandal_leaf, sandal_leaf, ximu_leaf)
  └── data/
      ├── celestium/
      │   ├── loot_table/blocks/           # 方块掉落物表 (含精准采集、双层半砖判定)
      │   ├── recipe/                      # 木板、台阶、楼梯无序/有序合成配方
      │   └── worldgen/                    # configured_feature, placed_feature, biome
      └── minecraft/
          └── tags/
              ├── block/                   # dirt.json, logs.json, planks.json, wooden_stairs.json, wooden_slabs.json
              └── item/                    # planks.json, wooden_stairs.json, wooden_slabs.json
  ```

---

## 3. 项目进度以及交接手册

- **当前开发状态**：
  - **苍穹平原地貌与植被生态**：苍穹草方块、苍穹泥土、苍穹石头、苍穹野草（噪波斑块生成）完全就绪。
  - **三大神木体系（Red Sandal、Sandal、Ximu）全面闭环**：
    - **红檀木（降真香，Red Sandalwood）**：原木、树叶、树苗、木板、台阶、楼梯、落叶粒子、配方、标签全套就绪。
    - **白檀木（Sandalwood）**：原木、树叶、树苗、木板、台阶、楼梯、落叶粒子、配方、标签全套就绪。
    - **析木（Ximu Wood）**：原木、落叶树叶、树苗、木板、台阶、楼梯、落叶粒子、配方、标签全套就绪。
  - **自然生成（Worldgen PlacedFeature）已打通**：
    - 苍穹平原成功挂载了苍穹草丛以及红檀、白檀、析木的自然生成规则，能在浮空岛地表自然生长出错落有致的仙木群落。
  - **创造栏（Item Groups）完整收录 21 项**：
    - `celestium_tab` 按照「地表方块 $\to$ 红檀全套 $\to$ 白檀全套 $\to$ 析木全套 $\to$ 仙石」标准顺序排列。

- **已完成功能清单**：
  - [x] 天界维度（`celestium:celestium`）与浮空岛原型搭建。
  - [x] 适配 1.21.11 天空与迷雾属性（`EnvironmentAttributeMap`）。
  - [x] 注册 `celestial_grass_block`、`celestial_dirt`、`celestial_stone`。
  - [x] 注册 `celestial_grass`（苍穹野草）及群系自然散布 Feature。
  - [x] 建立木材体系标准注册线：`CelestiumWoodBlocks`。
  - [x] 完成第一套木材：红檀木体系（`red_sandal_*` 全 6 件套）。
  - [x] 完成第二套木材：白檀木体系（`sandal_*` 全 6 件套）。
  - [x] 完成第三套木材：析木体系（`ximu_*` 全 6 件套）。
  - [x] 木制品衍生建材标准规范化：所有木材产出对应台阶与楼梯，打通双层台阶掉落、合成配方及原版木质标签。
  - [x] 解决 Blockbench elements 缺少 cullface 导致的帧率暴跌隐患，全套方块/树叶重构为严格继承原版父模型。
  - [x] 沉淀全流程自动化 Skill：`minecraft-tree-builder`。
  - [x] 树木自然放置（PlacedFeature）落地：在 `celestial_plains.json` 中完整挂载三大树木放置规则。
  - [x] 攻克树木生成参数壁垒：查明并解决 `CherryTrunkPlacer` 源码 `[1, 3]` 数量上限约束、`fancy_foliage_placer` 的 2 层空气悬空 Bug 及 7 格衰败距离限制。
  - [x] 创造栏收录完毕：`celestium_tab` 成功纳管全部 21 种方块物品。

- **下一步开发计划（TODO）**：
  - [ ] **自定义树干生成器（CelestiumTrunkPlacer）开发**：
    - 若后续需要突破 Mojang 原版 3 根樱花分枝的硬编码限制，可在模组内继承 `TrunkPlacer` 实现可自由配置 4~8 根大跨度分枝的专属天界神木生成器。
  - [ ] **树皮与去皮木材套件（Stripped Logs & Wood）**：
    - 制作去皮红檀木（`stripped_red_sandal_log`）、去皮白檀木、去皮析木，并实现斧头右键剥皮交互。
    - 制作六面全原木纹理方块（`wood` 及 `stripped_wood`）。
  - [ ] **木制品功能部件拓展（Fences, Gates, Doors, Trapdoors）**：
    - 制作各木种对应的栅栏、栅栏门、木门、活板门、压力板与按钮。
  - [ ] **更多天界生物群系（Biomes 扩展）**：
    - 设计天界山地（`celestial_peaks`）、天界密林（`celestial_forest`）等，将不同树种分区作为优势种散布。

- **交接操作指引**：
  - **运行 DataGen**：`$env:JAVA_HOME = "C:\Java_JDKs\Adoptium_Temurin\jdk-25.0.4.1+1"; .\gradlew runDatagen`
  - **打包模组**：`.\gradlew build`（输出包位于 `build/libs/celestium-1.0.0.jar`）
  - **进入游戏测试**：`.\gradlew runClient`，进游戏后输入 `/execute in celestium:celestium run tp ~ 100 ~` 即可漫游苍穹平原并检验自然神木。

---

## 4. 所涉及外部引用

- **核心运行与开发依赖**：
  - Minecraft: `1.21.11` (`net.minecraft`)
  - Fabric Loader: `>=0.19.5`
  - Fabric API: `0.141.6+1.21.11`
  - Fabric Loom: `1.18.2` (via `1.18-SNAPSHOT`)
  - Adoptium Temurin JDK 25 (`jdk-25.0.4.1+1`)
- **关键参考**：
  - [Misode Minecraft Worldgen & Loot Table Tool](https://misode.github.io/)：1.21.11 数据驱动与 PlacedFeature 配置规范。
  - [Blockbench Documentation](https://www.blockbench.net/wiki/)：Minecraft Java 方块 Display、UV 映射及模型面剔除优化。
  - Minecraft 1.21.11 内部源码：`net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer`、`net.minecraft.world.level.block.LeavesBlock`。

