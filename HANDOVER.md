# 📘 CELESTIUM 项目记忆与交接手册

> **最后更新时间**：2026-10-27 16:45  
> **分支**：`CODEX`  
> **更新概要**：
> 1. 全面攻坚并落地 **22 只仙界神兽与天庭实体「解剖级树状多关节体系架构」**，包含多级级联骨骼（头身尾爪翅鬃）、独立 2D 矩形装箱 UV（0 重叠 / 0 溢出）、动力学动画系统（`setupAnim`）及配套全套高精像素纹理（128x128 / 64x64 / 256x256）。
> 2. 完成 **29 种仙灵异草与神木像素重构**，严格执行 0/255 二值透明通道治理黑边，落地底部生根与多阶明暗色相过渡。
> 3. 彻底根除 1.21.11 天界维度生成致命崩溃 **`Feature order cycle found`（地貌特征拓扑序死锁）**，建立全维度统一的无环排序铁律；全量打通 **10 大天界生物群系** 与 Multi-noise 维度生成。
> 4. 接入完整 **仙界音效系统（16 组 Sound Events、sounds.json、双语字幕）**、**神兽防离场刷没持久化机制（Anti-Despawn）** 与 **22 实体掉落物表（Loot Tables）**。
> 5. 清理根目录构建脚手架，将全部代码生成、装箱与 UV 校验工具统一归拢收纳至 `src/main/generated/`。
> 6. **下一步核心重心已明确移交至**：以“细细拆分，拆了一层还能拆一层”的解剖级建筑体系，全面启动「太虚迎仙阁·溯凡亭」等天界建筑群的精细化建模构筑与落地。

---

## 1. 项目中特殊要求

- **用户开发规范与偏好**：
  - **严禁擅自修改用户核心意图**：始终以诊断、架构指导、原理解释和就绪代码块为先，由用户主导代码改动。
  - **生成新代码前必须先读参考代码**：生成新实体、新方块或新架构代码前，必须先完整查阅当前项目已有的标杆源码（如 `QilinModel.java`、`CelestiumRedSandalBlocks.java`），严格模仿用户的排版格式、缩进与命名习惯。
  - **严格杜绝自以为是的冗长注释**：严禁在代码中堆砌多余的 AI 教学或解释性长注释，仅保留类似 `// <id>_log`、`// setupAnim` 这样纯粹极简的小节标头注释。**一切以用户的现有代码为最高标准！**
  - **零容错原则**：生成完毕必须保证 Gradle 编译零语法报错、JSON 格式完全合规、UV 无越界。
  - **美术与资源管理偏好**：
    - 方块/物品 3D 模型优先在 Blockbench 中雕琢或由原版模型继承；
    - 实体模型采用多立方体解剖树架构，UV 严格独立规划；
    - 数据驱动配置（Loot Table、Tags、Worldgen）可灵活手写或由 DataGen 批量维护。
  - **语言翻译规范**：树木生成器及模组常规维护中需保证英文文件（`en_us.json`）完全覆盖，中文语言文件（`zh_cn.json`）需同步补全实体名称、音效字幕及方块物品名，避免未翻译键泄漏。

- **环境与构建链限制**：
  - **JDK 版本要求**：构建工具链使用 `fabric-loom:1.18.4`，必须锁定 **Java 25+**（Adoptium Temurin `jdk-25.0.4.1+1`）。终端执行 Gradle 命令时需注意 `$env:JAVA_HOME`。
  - **VS Code / PowerShell 设置项**：Windows 绝对路径切勿包裹外层双引号 `"`。
  - **缩进规范**：严格采用 **4 个空格**。

- **核心机制与开发避坑指南（Minecraft 1.21.11）**：
  - **AccessWidener 铁律**：
    - `src/main/resources/celestium.accesswidener` 必须为 **UTF-8 无 BOM 格式**。
    - **严禁盲目注入不存在的方法**：如 1.21.11 中 `SpawnPlacements` 注册方法签名已有重构，胡乱注入会导致 `validateAccessWidener` 崩溃。目前保留 `NoiseRouterData floatingIslands`，新增 `accessible field net/minecraft/world/entity/LivingEntity jumping Z`。
  - **Worldgen 拓扑序死锁铁律（Feature Order Cycle）**：
    - 在 1.21.11 维度生成中，Minecraft 在生成各区块的植被装饰阶段（`VEGETAL_DECORATION`，第 9 步）会对所有群系包含的 PlacedFeature 执行**有向无环图（DAG）全局拓扑排序**。
    - **崩溃根源**：若群系 A 中 Feature 顺序为 `[草, 树]`，而群系 B 中顺序为 `[树, 草]`，排序器将产生循环依赖，抛出 `java.lang.IllegalStateException: Feature order cycle found` 并彻底卡死游戏。
    - **全局统一标准序**：模组内所有群系在 `VEGETAL_DECORATION` 阶段必须严格执行单向依赖序列：
      `Trees (树木) -> Patch Celestial Grass (草地基础斑块) -> Herbs & Flowers (仙草灵花) -> Lanterns & Burners (宫灯与香炉)`。
  - **实体多立方体 UV 装箱与贴图严格 1:1 铁律**：
    - 实体模型创建 `LayerDefinition.create(meshdefinition, texW, texH)` 中的宽高必须与实体 PNG 贴图尺寸绝对一致（禁止模型声明 128x128 贴图却为 64x64）。
    - 实体模型的所有 Cuboid 必须执行 2D 矩形装箱（Box-packing），**0 个立方体溢出画布边界、0 对立方体共用非镜像 UV**。
    - 贴图需保持透明通道严格二值化（Alpha 0 或 255），杜绝因半透明像素产生的黑边（Black-outline Artifacts）。
  - **稀有神兽防离场刷没机制（Anti-Despawn & Persistence）**：
    - 大型神兽与仙禽（如云鲸、苍龙、烛龙、白象、凤凰、麒麟、仙鹿、白泽等）为仙境稀有造物，绝不能被原版“玩家离开远距离后自动销毁生物”机制当成普通小怪刷没。
    - 实体类中必须重写：
      ```java
      @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) { return false; }
      @Override public boolean requiresCustomPersistence() { return true; }
      ```
    - 在 `CelestiumWorldSpawner.java` 中配置 `isImportantRareBeast()`，防止周期性自然刷新算法将其当做无用实体丢弃。
  - **模型继承与面剔除铁律（防帧率暴跌）**：
    - 原木、木板、树叶、台阶、楼梯模型**必须严格继承原版父模型（`cube_column`、`cube_column_horizontal`、`cube_all`、`slab`、`slab_top`、`stairs`、`inner_stairs`、`outer_stairs`）**。
    - 严禁使用缺少 `cullface` 的手工拼凑方块，防止成片建筑和树林内部产生数万个无效渲染面导致 FPS 暴跌。
  - **楼梯 40 状态 BlockState 规范**：
    - 自定义楼梯（如 `celestial_glazed_stairs`）必须包含 `inner`（内转角）、`outer`（外转角）和 `straight`（直型）三套模型，并在 blockstate 中完整配置 40 种朝向/翻转组合（倒置楼梯需配 `x: 180`），杜绝 Missing Variant 警告。
  - **树叶 7 格衰败距离（Leaves Decay Distance）铁律**：
    - 原版 `LeavesBlock` 的 `distance` 属性上限为 **7**。任何树叶距离原木超过 7 格必会凋零自毁。树冠横向扩展必须通过原木分枝（`branch_horizontal_length`）支撑，且新原木必须立即加入 `data/minecraft/tags/block/logs.json`。
  - **方块物品语言前缀规范**：
    - 注册 `BlockItem` 时必须调用 `new Item.Properties().setId(itemKey).useBlockDescriptionPrefix()`，保证翻译键严格统一为 `block.<modid>.<name>`。

---

## 2. 项目总体规划和设计

- **模组名称与定位**：
  - **名称**：`CELESTIUM`（中文主题：中式天界 / 仙霄 / 浮空仙山）。
  - **Mod ID**：`celestium`。
  - **技术栈**：Minecraft 1.21.11、Fabric Loader 0.19.5、Mojang Official Mappings (Mojmap)、Fabric Loom 1.18.4、Adoptium Temurin JDK 25。

- **核心架构：分层模块化设计**：
  1. **注册总线（Registry）**：
     - `registry/block/CelestiumBlocks.java`：方块总入口，聚合 Flora、Wood、Herb、Decorative 四大子线。
     - `registry/block/CelestiumFloraBlocks.java`：基础仙境地表岩石土壤（`celestial_grass_block`、`celestial_dirt`、`celestial_stone`、`lunar_jade_stone`、`cinnabar_stone`、`thunder_jade_stone` 等）。
     - `registry/block/CelestiumWoodBlocks.java`：三大神木总线（红檀木、白檀木、析木全套木板、台阶、楼梯）。
     - `registry/block/CelestiumHerbBlocks.java`：20 种仙草灵花（九叶灵芝、紫云芝、太阳参、赤血参、悟道茶、雪莲花、玉莲、金莲等）。
     - `registry/block/CelestiumDecorativeBlocks.java`：12 种天庭中式建筑构件（蟠龙华表、青铜香炉、白玉栏杆、宫灯、琉璃瓦全套、风铃、供案石桌）。
     - `registry/CelestiumItemGroups.java`：创造栏专属标签页 `celestium_tab`，收录全方块、全道具与全部 22 种仙兽刷怪蛋。
     - `entity/CelestiumEntities.java`：22 种仙界实体类型注册总线。
     - `sound/CelestiumSounds.java`：16 组专属生物与环境音效注册总线。
  2. **客户端渲染与动效层（Client）**：
     - `client/CelestiumClient.java`：统一管理 CUTOUT / TRANSLUCENT 渲染图层、22 组 `EntityModelLayer` 注册与 `EntityRenderer` 绑定。
     - `client/entity/model/*Model.java`：22 种实体树状多关节解剖模型（均带多相移、波浪运动学及惯性滞后动画系统）。
     - `client/entity/renderer/*Renderer.java`：神兽实体渲染器（含云鲸 11x、烛龙 6.5x、苍龙 6.2x、白象 2.65x、凤凰 2.6x 等动态巨兽缩放与阴影半价优化）。
  3. **世界生成与群系层（Worldgen）**：
     - `dimension/celestium.json`：天界多噪声维度（Multi-noise Biome Source），高度 `min_y: 0, height: 256, sea_level: 0`。
     - `worldgen/biome/`：**10 大天界生物群系**：
       - `celestial_plains`（太虚仙原 / 苍穹平原 - 核心母港）
       - `yaochi_pool`（瑶池仙境）
       - `sunset_sandal_forest`（落霞檀林）
       - `lunar_frostpeak`（广寒冰峰）
       - `vermilion_peak`（赤霄朱雀峰）
       - `ziwei_cloud_domain`（紫微云海）
       - `penglai_bamboo_sea`（蓬莱竹海）
       - `peach_orchard`（蟠桃仙苑）
       - `beidou_star_plain`（北斗星原）
       - `celestial_thunder_peak`（九天雷霄峰）
     - `worldgen/structure/`：**10 大天界预制遗迹**：
       - `celestial_pavilion`（问仙台）
       - `south_heavenly_gate`（南天门）
       - `lingxiao_celestial_palace`（凌霄天宫）
       - `alchemy_crucible_shrine`（炼丹仙坊）
       - `scripture_pagoda`（藏经宝塔）
       - `cloud_rainbow_bridge`（云海虹桥）
       - `moon_watching_altar`（望月台）
       - `seven_treasure_terrace`（七宝琉璃台）
       - `star_observatory_altar`（观星祭坛）
       - `thunder_god_pavilion`（雷神阁）

---

## 3. 项目进度以及交接手册

- **当前开发状态**：
  - **神兽解剖级重构 100% 达成**：22 种仙兽与天兵实体全部完成多立方体级联骨骼重构，彻底告别单块方盒模型，支持拟真关节屈伸、波浪游动、展翼振翅与呼吸起伏；全贴图 UV 100% 严密对齐。
  - **植物美学全面飞跃**：29 种灵草、灵芝、人参、水生莲花及仙藤全部采用全新细腻像素重构，消除 CUTOUT 模式下的黑色边缘假影。
  - **天界维度运行平稳**：修复了世界生成中的拓扑环死锁，10 大生物群系均能自然生成且地貌特征稳定互不干扰。
  - **声音与交互闭环**：添加了全套音效事件并接入实体生命周期；添加了防刷没持久化机制与实体掉落物表（掉落对应灵草、内丹与仙料）。
  - **生产工具资产库收纳**：所有 Python 模型生成、UV 装箱计算与检验脚本均已规整于 `src/main/generated/`，保持根目录纯净。

- **已完成功能清单**：
  - [x] 天界维度（`celestium:celestium`）与浮空岛原型搭建（1.21.11 适配）。
  - [x] 修复 `celestium.accesswidener` 异常方法注册，加入 `LivingEntity jumping Z`。
  - [x] 建立木材体系标准注册线：红檀、白檀、析木全 6 件套及楼梯台阶衍生规范。
  - [x] 解决 1.21.11 `Feature order cycle found` 循环死锁，实现 Kahn 算法检验的无环 Feature 拓扑序。
  - [x] 修复琉璃瓦楼梯 40 项 Blockstate 变体缺失与内/外转角模型。
  - [x] 重构 29 种灵花灵草像素材质，严格落地二值透明度（0/255）与底部接地设计。
  - [x] 修复巡天云鲸尾部 8 像素空气间距断层，重构为 4 节无缝级联尾部并挂载须弥宝座与双翼羽片。
  - [x] 22 只仙界实体解剖级多关节树状模型重构（包含金乌三足、白象三节鼻与六牙、九尾狐独立九尾、玄龟蛇尾与洛书、白泽三目羊角等）。
  - [x] 22 只仙界实体 1000+ 个 Cuboid 完成 2D 矩形装箱，100% 达到 0 溢出、0 错位。
  - [x] 调整 6 大巨兽模型缩放倍率（1.85x ~ 11.0x）、碰撞箱（Hitbox）与骑乘位坐标（Rider Offset）。
  - [x] 注册 16 组仙界音效事件（`CelestiumSounds`），配置 `sounds.json`，补充中英双语字幕。
  - [x] 实体接入环境/受击/死亡音效，为 12 种稀有巨兽引入 `removeWhenFarAway = false` 防刷没机制。
  - [x] 编写并部署全部 22 种实体的掉落物表（`loot_table/entities/*.json`）。
  - [x] 规整代码生成与测试脚本至 `src/main/generated/`。
  - [x] 成功构建 `celestium-1.0.0.jar` 并实时部署至 Prism Launcher 启动器目录。

---

## 4. 下一步开发计划（TODO —— 核心聚焦：天界建筑体系精细化构筑）

用户已明确提出核心设计思想：**“仿照解剖级思路做建筑，细细拆分，拆了一层还能拆一层”**。下一步模组的开发重心将全面围绕**“天界古典建筑体系的解剖化重构与落地”**展开：

- [ ] **太虚仙原核心地标构筑：【太虚迎仙阁 · 溯凡亭】**：
  - 作为玩家飞升进入太虚仙原的第一眼震撼与情感锚点，运用解剖级分层方式进行精细构筑：
    1. **第 1 层 · 须弥基座与丹墀层**：雕花白玉砖收底 ➡️ 束腰镶嵌暖日光石 ➡️ 琉璃压栏石 ➡️ 双侧琉璃踏道与中央九龙飞天御道浮雕 ➡️ 白玉雕花栏杆与螭首排水。
    2. **第 2 层 · 柱网木构身舍层**：红檀木外圈檐柱（带内倾 1 度的侧脚与生起工艺） ➡️ 内圈承重金柱 ➡️ 大额枋连接 ➡️ 倒置阶梯祥云雀替 ➡️ 三交六椀菱花格子门窗。
    3. **第 3 层 · 斗栱铺作层（核心力学与艺术灵魂）**：栌斗基盘 ➡️ 华栱横向展臂 ➡️ 30 度倾角下昂外挑 ➡️ 齐心斗咬合 ➡️ 耍头锁固挑檐，实现如翻滚云海般的斗栱层。
    4. **第 4 层 · 梁架举折层**：七架梁 ➡️ 瓜柱 ➡️ 五架梁 ➡️ 三架梁 ➡️ 举折递减曲线，塑造自然流体下泻的屋顶骨架。
    5. **第 5 层 · 翼角起翘与琉璃屋面层**：青金琉璃瓦阴阳扣合瓦陇 ➡️ 45 度辐射角梁起翘飞檐 ➡️ 正脊鸱吻镇水兽 ➡️ 戗脊仙人骑凤与五样仙兽陶俑（青鸾、麒麟、天马、獬豸、貔貅） ➡️ 飞檐悬挂惊鸿风铃。
    6. **第 6 层 · 斗八藻井与室内仙家陈设**：仰望穹顶的三层八角斗八藻井 ➡️ 俯冲蟠龙吐微光灵珠 ➡️ 沉香宝座与六折青绿山水屏风 ➡️ 青铜云纹香炉（吐出青烟粒子） ➡️ 天庭琉璃宫灯照拂。
    7. **第 7 层 · 物哀留白与环境叙事嵌入**：
       - 后廊悬空飞桥连接的「溯凡亭」（悬崖六角小亭、石桌冷茶、未完残局，眺望虚空）；
       - 殿柱上顽强愈合创口的「朝暮藤」；
       - 斗栱背风角留出的「仙鹤筑巢小窝」。
- [ ] **现存 10 大 NBT 遗迹架构升级与规范校验**：
  - 检查并升级 `south_heavenly_gate.nbt`、`lingxiao_celestial_palace.nbt` 等遗迹结构，消除手工拼接碎面，融入天界新方块（白玉栏杆、琉璃瓦套件、香炉、华表）。
- [ ] **结构生成池（Jigsaw / Structure Template Pool）接入**：
  - 将精制完成的仙阁与遗迹接入 1.21.11 的数据包生成池，确保在太虚仙原及其他群系中以合理稀有度自然生成。
- [ ] **自定义树木生成器（CelestiumTrunkPlacer）开发**（后续储备）。
- [ ] **树皮与去皮木材套件（Stripped Logs & Wood）**（后续储备）。

---

## 5. 交接与验证操作指引

- **验证 Java 25 编译**：
  ```powershell
  $env:JAVA_HOME = "C:\Java_JDKs\Adoptium_Temurin\jdk-25.0.4.1+1"
  $env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
  ./gradlew compileJava --rerun-tasks
  ```
- **全量打包构建**：
  ```powershell
  ./gradlew build
  ```
  产出 jar 位于 `build/libs/celestium-1.0.0.jar`。
- **Prism Launcher 联调部署**：
  打包后的 jar 自动同步复制到：
  `D:/Minecraft/.launcher/Prism/instances/CELESTIUM/minecraft/mods/celestium-1.0.0.jar`
- **进入游戏测试**：
  通过 Prism Launcher 启动 `CELESTIUM` 实例，进入游戏后执行：
  `/execute in celestium:celestium run tp ~ 100 ~`
  即可在太虚仙原漫游，观察各类神兽动态、音效、掉落及灵草分布。

---

## 6. 所涉及外部引用

- **核心运行与开发依赖**：
  - Minecraft: `1.21.11` (`net.minecraft`)
  - Fabric Loader: `>=0.19.5`
  - Fabric API: `0.141.6+1.21.11`
  - Fabric Loom: `1.18.4`
  - Adoptium Temurin JDK 25 (`jdk-25.0.4.1+1`)
- **关键设计参考与规范**：
  - 中国古典营造学社《营造法式》（李诫 著）与《清式营造则例》（梁思成 著）：斗栱铺作、抬梁式木构、举折反宇与须弥座营造法式。
  - Minecraft 1.21.11 内部源码：`net.minecraft.world.level.levelgen.feature.FeatureSorter`（DAG 拓扑排序校验）、`net.minecraft.world.entity.SpawnPlacements`。
