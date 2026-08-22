<div style="text-align: center;"><h1>ExpoClient OpenSource</h1></div>

<p align="center">
  <img src="./img/killexpo.png" alt="1" width="20%" />
<img src="./img/czh.gif" alt="蔡子豪本人" width="26%">
</p>



<p align="center">
  <img src="./img/deobf.jpg" alt="1" width="20%" />
  <img src="./img/deobf2.jpg" alt="2" width="20%" />
  <img src="./img/obj.jpg" alt="3" width="20%" />
  <img src="./img/nixiang.jpg" alt="4" width="12%" />
  <img src="./img/gay.png" alt="1" width="16%" />   
  <img src="./img/chuying.png" alt="2" width="16%">
  <img src="./img/fuck.jpg" alt="3" width="16%">
</p>

## 细节

<p align="center">
  <strong>简体中文</strong> |
  <a href="./README.en.md">Idiomatic American English™</a>
</p>

ExpoClient 是由来自**美利坚留学生**蔡子豪(又称精神美利坚人)和**他的ai**于公元2026年公开售卖基于的[OpenMyau](https://github.com/Mornly/OpenMyau-Fix)和raven缝合的我的世界外挂客户端，目标版本为 Minecraft 1.8.9 + Forge。

经过Claude™长达16秒的分析得出：Expo使用了[zkm26](https://zelix.com/klassmaster/index.html)、[jnic351](https://jnic.dev/)、[XiaoShadiao-Obfuscator](https://github.com/SuperShadiao/XiaoShadiao-Obfuscator)、[幻影盾](https://www.skidonion.tech/)、[VMProtect](https://vmpsoft.com/)、[Themida](https://www.oreans.com/themida.php) 对客户端进行了保护，需要注意的是除了幻影盾和开源的xiaoshadiao以外的所有保护程序均为**破解版**。

购买此客户端即可获得zkm+jnic+XiaoShadiao+幻影盾+vmp+tmd 6种强劲保护。

在Claude™和人工手动的情况下对目标客户端进行了包括但不限于: **ZelixKlassMaster26破解版**还原大部分类的字符串加密、invokedynamic(all)、反射、控制流混淆(精力有限没多余时间做到100%解混淆)。
并将由**VMProtect Lite 3.10.4破解版**保护的`expoantidump.dll`脱壳。
以及**JNIC**的native方法还原、常量还原。并且脱壳+去虚拟化了**幻影盾**所使用的从古墓中挖出得**Themida**破解版后将JNI方法还原并解密还原了所有类。

**由于没那么多的时间精力做到100%解混淆以及各项功能测试，希望有意愿有能力的朋友可以加入到我们，向我们提出pr为openexpo贡献出自己的一份力。**
但大部分类依然是处于可读状态且可直接构建启动。

最终在使用 **Opus5™ 和 Fable5™** 对本项目经过修复，便得到了这份源码。

<p align="center">
  <img src="./img/killphantomshield.png" alt="1" width="40%" />
</p>

## 假如给我三天圈钱
注意，本客户端**可能**因为蔡子豪不会编写绕过和视觉(自己写的部分纯属ai意淫出来的，skid的也由ai完成打滑)，在病症观察过程中所做出来的言行举止可能患有[智力发育障碍](https://zh.wikipedia.org/wiki/智能障礙)，且售价不应该是一个这样子的垃圾打滑客户端所拥有的，远远高于其得到的价值，所以我们决定将其开源。

暂且不知道是不是由于蔡子豪**脑淤血**还是**美利坚留学贫困生**，用其脑回路不正常的思维秉承着赚美刀花美刀的理念定价(来自roseshop): **每个月高达60cny**，**永久140cny**，热注入版本**单独售卖一年170cny**的去出售一份客户端。(截止项目开源时间，已有至少400名受害者)

<p align="center">
  <img src="./img/2dollar.png" alt="1" width="40%" />
  <img src="./img/xx.jpg" alt="1" width="40%" />
</p>
<p align="center">
  <img src="./img/quanqian.png" alt="1" width="80%" />
</p>


## 光辉事迹

### 蔡子豪之倒卖高价Minecon并成为诈骗犯
精神美利坚人早年因为诈骗和倒卖Minecon账户，人人喊打之下顺带被出露全家户籍并退网，随即销声匿迹。现已靠巴结和意淫精神胜利装逼，在观察其光荣事迹之后我们难免对其~~父母~~感到**无奈与惋惜**

<p align="center">
  <img src="./img/homeless.jpg" alt="1" width="25%" />
</p>

- 搞笑视频----**[美国街道哥NHCM账号打雷了](https://b23.tv/YLak7rm)**
- 搞笑视频第二则----**[美国街道NHCM爱跳舞，就像老鼠爱大米](https://www.bilibili.com/video/BV1wkoUYZEQ5/)**
- 搞笑视频第三则----**[NHCM的小仁被正义了](https://www.bilibili.com/video/BV1JadpY1EoU/)**

### 蔡子豪之如何写端
精神美利坚人在2025年复活后询问KillAura转头如何编写，随着AI技术进步此人发表诸如**MegaWalls 最强客户端**相关视频和随便殴打其他客户端等相关言论，在其最近视频直播中，**大量出现40ms地堡Switch但是下方经验值依然为0**，其外挂打Legit都费劲，精神美利坚人用自己外挂直播依旧丑态百出，甚至外挂都开不明白。
为什么还会有这么多的用户选择购买与捧臭脚: 随即我们实际上手体验并查看其代码水平以及收集蔡子豪发表的言论后，结果让笔者**大跌眼镜**。

<p align="center">
  <img src="./img/nick.jpg" alt="1" width="50%" />
  <img src="./img/jiyan1.png" alt="1" width="40%" />
</p>
<p align="center">
  <img src="./img/baoda.jpg" alt="1" width="50%" />
  <img src="./img/jiyan3.png" alt="1" width="40%" />
</p>
<p align="center">
  <img src="./img/jiyan2.png" alt="1" width="60%" />
</p>
<p align="center">
  <img src="./img/question.jpg" alt="1" width="50%" />
</p>

### 蔡子豪之阴谋论
早期的精神美利坚人刚开始写端时，外挂惨遭破解。后来发布了一条[视频](https://www.bilibili.com/video/BV11KN56AEQ1)回应外挂被破解的事实，在视频中其专业知识的匮乏让笔者为此人的智商感到堪忧。在没有拿出绝对证据的情况下仅凭加了vmp壳和沙盒的静态分析就推断出破解程序具有后门的结论(**哪怕即使真的有，也不应该以次就得出结论**)。

<p align="center">
  <img src="./img/MalwareX.png" alt="1" width="40%" />
  <img src="./img/wdymVMP.png" alt="1" width="40%" />
</p>

如果按照蔡子豪的大神分析操作，那么expo.jar里面的 `expodll/expoantidump.dll` 是否也是后门呢？(不过事实是经过我们对`expoantidump.dll`脱壳分析后得出没有后门的结论)

<p align="center">
  <img src="./img/antidumpdll.png" alt="1" width="40%" />
  <img src="./img/vmp.png" alt="1" width="40%" />
  <img src="./img/virustotal.png" alt="1" width="70%" />
</p>

### 蔡子豪之理解美国人成为美国人超越美国人
当上美利坚留学生后的蔡子豪可能脑袋在美利坚被车创飞认不清自己的国籍身份，发表诸如歧视中国人、歧视身份地位、歧视学历并赞扬美国科技水平之高超等言论，其言行令人怀疑他是否患有[精神分裂](https://zh.wikipedia.org/wiki/精神分裂症)，**一边在吸自己人的血一边崇洋媚外**，以及*可能*发表过辱华言论(存疑，待补充证据，欢迎提交Issues)。

<p align="center">
  <img src="./img/word.png" alt="1" width="40%" />
  <img src="./img/AmericanNB.png" alt="1" width="40%" />
</p>
<p align="center">
  <img src="./img/AmericanNB2.jpg" alt="1" width="40%" />
  <img src="./img/sima.png" alt="1" width="25%" />
</p>



## 朝花夕拾

### 公元2022年12月27日，早期的蔡子豪还不是精神美利坚人，在**湖北**的家中使用快手app薅羊毛，为后续成为美利坚贫困生埋上伏笔：

<p align="center">
  <img src="./img/91.jpg" alt="1" width="30%" />
</p>

### 与此同时，一年前的蔡子豪还是在MMC打pvp的**路边流浪汉**一条：

<p align="center">
  <img src="./img/llh.png" alt="1" width="50%" />
</p>

### 一年后摇身一变，成为一边用着**openmyau混合raven**的base开发expo外挂，一边提倡大家不要买myau的**路边乞丐**，对于这种吃饭砸锅的行为笔者暂且蒙在古里：

<p align="center">
  <img src="./img/nomyau.png" alt="1" width="40%" />
</p>

### 精神美利坚人唱歌中：

<p align="center">
  <img src="./img/singsong.png" alt="1" width="40%" />
</p>

## 环境要求

- **JDK 8**
- Gradle Wrapper 8.14.5（`./gradlew` 自动下载）
- **一份装了 Forge 的本地 Minecraft 1.8.9 目录**。和 `libraries/`
- 首次构建需联网，中国大陆可能需要vpn（Maven Central + `maven.minecraftforge.net` + `libraries.minecraft.net`）
- highiq

## 构建和运行

```
./gradlew build
```

产物：**`build/libs/expo-OpenSource.jar`**


```
./gradlew runClient
```

可以把产物直接丢进任意 1.8.9 Forge 启动器的 `mods/`。

**如果你是残疾人，可以让ai帮你。**

## IntelliJ IDEA

1. 打开工程目录，等 gradle 同步完成
2. 项目 SDK 选 JDK 8
3. `.run/` 下已有三份运行配置（Build / Expo Client / Remap Minecraft）。

## 协助我们与注意事项

> 本源码是尽力而为的反混淆结果，可能存在行为差异或 bug。虽然已经尽力修复，但无法保证 100% 还原。大部分符号和手动编写的还原代码可能和原作者命名意图不符。
>
> 值得说的是蔡子豪dev将相当一部分功能(外置gui、配置系统、命令系统等)通过native层集成，即使在脱去壳后我们也只是通过asm和字节码以及c2j并在对比原始客户端运行时后对其逻辑进行手工还原，此处的代码大部分和原版**并不一致**但使用体验上尽力而为保持相似，其他客户端主要功能(所有为java层的模块如视觉和绕过)均为手动解混淆后修复的结果。
>
> 并且目前我们并没有拿到热注入版本的相关样本，但已知的是功能并没有区别。可能只多了一个注入器，在源码分析中我们也看得出来此客户端有在注入层下功夫。所以注入器需要读者自行编写兼容。
>
> 欢迎贡献代码(pr)和反馈问题(issues)！

## 致谢

- **原始混淆客户端：** Expo 2.4.5 (在还原的过程时发布了2.4.6和2.4.7，更新已合并到当前开源源码。)
- **反混淆、符号恢复、还原：** 由 Claude™ 在人工监督下完成（精神美利坚人蔡子豪麻烦报销一下Claude™的token费用）
- **脱壳、去虚拟化、反混淆工具：** [**NoHackClient**](https://github.com/NoHackClient)™
- **Java Deobfuscator**
- **Recaf**
- **从古墓中挖出的 [Themida](https://www.oreans.com/)**
- **惨遭泄露的 [VMProtect](https://vmpsoft.com/)**
- **多年前惨死街头的 [JNIC](https://jnic.dev/)**
- **被还原源码的 [ZelixKlassMaster](https://zelix.com/klassmaster/index.html)**
- **蔡子豪亲自颁发野爹证的 [XiaoShadiao-Obfuscator](https://github.com/SuperShadiao/XiaoShadiao-Obfuscator)**
- **来自skidonion的 [幻影盾](https://www.skidonion.tech/)**

## 免责声明与许可

- **商标声明**：`Minecraft` 是 Mojang Studios / Microsoft Corporation 的注册商标。本项目提及的所有相关商标、服务标记及产品名称均归其各自所有者所有。
- **关联性声明**：本项目为独立的社区开发项目，**并非 MINECRAFT 官方产品**。本项目未获得 Mojang 或 Microsoft 的批准，亦与 Mojang 或 Microsoft 无任何关联。
- **资产与代码说明**：本代码仓库内未直接分发任何专有的 Minecraft 原创资产或未混淆的 Mojang 源代码。所有游戏资产及专有代码的版权均归其各自所有者所有。`mappings/` 下是 MCP `stable_22` 数据，版权归 MCP 团队所有。
- **第三方**：产物内含账号管理器代码，衍生自 [Lumiere](https://github.com/xanning/Lumiere)（MIT）。
  许可全文在 `src/main/resources/licenses/AccountManager-LICENSE`。
  产物内的 `com/formdev/**` 是 FlatLaf 3.2（Apache-2.0），由 Gradle 从 Maven Central 解析
  （`com.formdev:flatlaf:3.2`）后打进 jar，仓库里不存放它的二进制。
  ASM（BSD-3-Clause）不进产物：运行期由 Forge/LaunchWrapper 的 `org.ow2.asm:asm-all:5.0.3` 提供。
- **许可**：见 `LICENSE`。原始混淆字节码未授予任何许可。本仓库中的反混淆产物、构建脚本与文档仅供研究与学习使用。如果你是 Expo 的原作者并希望本仓库下架或重新授权，请提 Issues。**虽然提了也不鸟你**。
