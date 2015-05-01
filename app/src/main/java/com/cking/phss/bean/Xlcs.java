package com.cking.phss.bean;

public class Xlcs {
    public static final int YY = 0;
    public static final int JL = 1;
    public static final int PZ = 2;
    public static final int ZS = 3;
    public static final int UCLA = 4;
    public static final int SS = 5;
    public static final int SCL90 = 6;
    public static final int QLSCA = 7;

    public static final String[] YY_CONTENT = new String[] {
            "抑郁自评量表（SDS）|您感觉|我觉得闷闷不乐，情绪低沉|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "抑郁自评量表（SDS）|您感觉|我觉得一天中早晨最好。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "抑郁自评量表（SDS）|您感觉|我一阵阵哭出来或觉得想哭。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "抑郁自评量表（SDS）|您感觉|我晚上睡眠不好。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "抑郁自评量表（SDS）|您感觉|我吃得跟平常一样多。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "抑郁自评量表（SDS）|您感觉|我与异性密切接触时和以往一样感到愉快。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "抑郁自评量表（SDS）|您感觉|我发觉我的体重在下降。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "抑郁自评量表（SDS）|您感觉|我有便秘的苦恼。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "抑郁自评量表（SDS）|您感觉|我心跳比平常快。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "抑郁自评量表（SDS）|您感觉|我无缘无故地感到疲乏。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "抑郁自评量表（SDS）|您感觉|我的头脑跟平常一样清楚。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "抑郁自评量表（SDS）|您感觉|我觉得经常做的事并没有困难。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "抑郁自评量表（SDS）|您感觉|我觉得不安而平静不下来。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "抑郁自评量表（SDS）|您感觉|我对将来抱有希望。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "抑郁自评量表（SDS）|您感觉|我比平常容易生气激动。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "抑郁自评量表（SDS）|您感觉|我觉得做出决定是容易的。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "抑郁自评量表（SDS）|您感觉|我觉得自己是个有用的人，有人需要我。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "抑郁自评量表（SDS）|您感觉|我的生活过得很有意思。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "抑郁自评量表（SDS）|您感觉|我认为如果我死了，别人会过得好些。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "抑郁自评量表（SDS）|您感觉|平常感兴趣的事我仍然感兴趣。|没有或很少时间;少部分时间;相当多时间;绝大部分或全部时间|4;3;2;1", };
    public static final String[][] YY_CSJG = new String[][] {
            { "0", "0.5", "无抑郁", "暂无分析说明", "你的精神状态比较好，请保持状态。" },
            {
                    "0.5",
                    "0.6",
                    "轻度抑郁",
                    "暂无分析说明",
                    "情绪时常感到低落，会感到毫无愉快心情，且持续时间一段时间，兴趣下降，对以往非常喜欢的东西兴趣变弱，会有活着太累的想法，工作能力下降，认为自己无用、无能，缺少价值感，甚至会哭泣、流泪，经常有疲倦感，食欲不振，睡眠欠佳、早醒，性欲也下降。" },
            {
                    "0.6",
                    "0.7",
                    "中度抑郁",
                    "暂无分析说明",
                    "情绪低落比较明显，经常感到毫无愉快心情，且持续时间也比较长，兴趣下降明显，对以往非常喜欢的东西则毫无兴趣，常有活着太累的想法，工作能力明显下降，常认为自己无用、无能，缺少价值感，甚至会哭泣、流泪，经常有疲倦感，食欲不振，睡眠欠佳、早醒，性欲也明显下降。" },
            {
                    "0.7",
                    "1",
                    "重度抑郁",
                    "暂无分析说明",
                    "情绪非常低落，感觉毫无生气，没有愉快的感觉，经常产生无助感或者绝望感，自怨自责。经常有活着太累，想解脱、出现消极的念头，还常哭泣或者整日愁眉苦脸，话语明显少，活动也少，兴趣缺乏，睡眠障碍明显，入睡困难或者早醒，性欲功能基本没有。" } };

    public static final String[] JL_CONTENT = new String[] {
            "焦虑自评量表（SAS）|您觉得|我觉得比平常容易紧张和着急|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我无缘无故地感到害怕|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我容易心里烦乱或觉得惊恐|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我觉得我可能将要发疯|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我觉得一切都好，也不会发生不幸|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "焦虑自评量表（SAS）|您觉得|我手脚发抖打颤|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我因为头痛、头颈痛和背痛而苦恼|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我感觉容易衰弱和疲乏|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我觉得心平气和，并且容易安静坐着|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "焦虑自评量表（SAS）|您觉得|我觉得心跳得很快|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我因为一阵阵头晕而苦恼|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我有晕倒发作或觉得要晕倒似的|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我呼气、吸气都感到很容易|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "焦虑自评量表（SAS）|您觉得|我手脚麻木和刺痛|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我因为胃痛和消化不良而苦恼|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我常常要小便|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我的手常常是干燥温暖的|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "焦虑自评量表（SAS）|您觉得|我脸红发热|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4",
            "焦虑自评量表（SAS）|您觉得|我容易入睡，并且一夜睡得很好|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|4;3;2;1",
            "焦虑自评量表（SAS）|您觉得|我做噩梦|没有或很少时间;小部分时间;相当多时间;绝大部分或全部时间|1;2;3;4", };
    // 有点不明白
    // 量表协作组还对129例神经衰弱、焦虑性神经症和抑郁性神经症者进行了检查，
    // 得出SAS的平均总粗分为42.98±9.94。其中神经衰弱为40.52±6.62分，焦虑症为45.68±11.23分。
    public static final String[][] JL_CSJG = new String[][] {
            { "25", "50", "无焦虑", "暂无分析说明", "情绪状态比较好，请继续保持。" },
            { "50", "60", "轻度焦虑", "暂无分析说明",
                    "自感内心有时比较烦乱，与以往比较，容易出现紧张和害怕，有时会出现心跳快，心慌，还可能有躯体上不舒服的感觉，诸如头痛、头和背有紧绷感，或胃部不适、食欲减退，经常做梦等，睡眠有些困难，易惊醒。" },
            { "60", "70", "中度焦虑", "暂无分析说明",
                    "自感内心烦躁明显增多，经常容易害怕或出现紧张、焦虑等情绪反应，也有躯体上不舒服感，如心慌、心跳快，偶有呼吸比较急，还会有头痛、头晕、背部的紧绷感，经常会坐立不安，常有饮食欠佳，消化不良。" },
            {
                    "70",
                    "100",
                    "重度焦虑",
                    "暂无分析说明",
                    "经常无端地感到焦虑、害怕和紧张，感到内心的烦恼，似乎要有事情发生，所以心跳加快，心慌，显得坐立不安，常有感到自己会失控或者发病的感觉。经常伴有胃部不适，食欲不振。夜眠差，多梦，易惊醒。或入睡困难等。" } };

    public static final String[] PZ_CONTENT = new String[] {
            "匹兹堡睡眠质量指数（PSQI）|HAD-A（SAS）为焦虑分表|我感到紧张（或痛苦）|几乎所有时候;多数时候;有时;根本没有|3;2;1;0",
            "匹兹堡睡眠质量指数（PSQI）|HAD-D（SDS）为抑郁分表|我对以往感兴趣的事情还是有兴趣|肯定一样;不像以前那样多;只有一点;基本上没有了|0;1;2;3",
            "匹兹堡睡眠质量指数（PSQI）|HAD-A（SAS）为焦虑分表|我感到有点害怕，好像预感到有什么可怕事情要发生|非常肯定和十分严重;是有，但并不太严重;有一点，但并不使我苦恼;根本没有|3;2;1;0",
            "匹兹堡睡眠质量指数（PSQI）|HAD-D（SDS）为抑郁分表|我能够哈哈大笑，并看到事物好的一面|我经常这样;现在已经不大这样了;现在肯定是不太多了;根本没有|0;1;2;3",
            "匹兹堡睡眠质量指数（PSQI）|HAD-A（SAS）为焦虑分表|我的心中充满烦恼|大多数时间;常常如此;并不;有时|3;2;1;0",
            "匹兹堡睡眠质量指数（PSQI）|HAD-D（SDS）为抑郁分表|我感到愉快|我经常这样;现在已经不大这样了;现在肯定是不太多了;根本没有|3;2;1;0",
            "匹兹堡睡眠质量指数（PSQI）|HAD-A（SAS）为焦虑分表|我能够安静而轻松地坐着|肯定;经常;并不经常;根本没有|0;1;2;3",
            "匹兹堡睡眠质量指数（PSQI）|HAD-D（SDS）为抑郁分表|我对自己的仪容（打扮自己）失去兴趣|肯定;并不像我应该做到的那样关心;我可能不是非常关心;我仍像以往一样关心|3;2;1;0",
            "匹兹堡睡眠质量指数（PSQI）|HAD-A（SAS）为焦虑分表|我有点坐立不安，好像感到非要活动不可|确实非常多;是不少;并不很多;根本没有|3;2;1;0",
            "匹兹堡睡眠质量指数（PSQI）|HAD-D（SDS）为抑郁分表|我对一切都是乐观地向前看|差不多是这样做的;并不完全是这样做的;很少这样做;几乎从来不这样做|0;1;2;3",
            "匹兹堡睡眠质量指数（PSQI）|HAD-A（SAS）为焦虑分表|我突然发现恐慌感|确实很经常;时常;并非经常;根本没有|3;2;1;0",
            "匹兹堡睡眠质量指数（PSQI）|HAD-D（SDS）为抑郁分表|我好像感到情绪在渐渐低落|几乎所有的时间;经常;有时;根本没有|3;2;1;0",
            "匹兹堡睡眠质量指数（PSQI）|HAD-A（SAS）为焦虑分表|我感到有点害怕，好像某个内脏器官变坏了|根本没有;有时;经常;大多数时间|0;1;2;3",
            "匹兹堡睡眠质量指数（PSQI）|HAD-D（SDS）为抑郁分表|我能欣赏一本好书或一项好的广播或电视节目|常常;有时;并非经常;很少|0;1;2;3", };
    public static final String[][] PZ_CSJG = new String[][] {
            { "0", "7", "睡眠正常", "暂无分析说明", "你的睡眠质量很好，请继续保持。" },
            {
                    "7",
                    "14",
                    "轻度睡眠障碍",
                    "暂无分析说明",
                    "请注意增加睡眠量，合理的睡眠量能解除疲劳，保持精神愉快；注意饮食习惯，晚餐不要吃得太饱，或空腹睡觉，这二种情况都会影响人的睡眠；睡觉前放松自己，睡前应避免从事刺激性的工作和娱乐，也不要从事过分紧张的脑力活动，做些能松弛身心的活动；不要让床成为你学习、工作的场所。躺在床上看书、看报，或谈些兴奋性的话题，会削弱床与睡眠的直接联系。创造一个良好的睡眠环境；采用合适的睡姿，睡眠的最好体位应该是右侧位或正平卧位，这样既不会压迫心脏，又利于四肢机体的放松休息。" },
            {
                    "14",
                    "100",
                    "睡眠障碍",
                    "暂无分析说明",
                    "请注意增加睡眠量，合理的睡眠量能解除疲劳，保持精神愉快；注意饮食习惯，晚餐不要吃得太饱，或空腹睡觉，这二种情况都会影响人的睡眠；睡觉前放松自己，睡前应避免从事刺激性的工作和娱乐，也不要从事过分紧张的脑力活动，做些能松弛身心的活动；不要让床成为你学习、工作的场所。躺在床上看书、看报，或谈些兴奋性的话题，会削弱床与睡眠的直接联系。创造一个良好的睡眠环境；采用合适的睡姿，睡眠的最好体位应该是右侧位或正平卧位，这样既不会压迫心脏，又利于四肢机体的放松休息。" } };

    // 自杀态度问卷（SAQ）
    public static final String[] ZS_CONTENT = new String[] {
            "自杀态度问卷（SAQ）||自杀是一种疯狂的行为|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||自杀死亡者应与自然死亡者享受同样的待遇|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||一般情况下我不愿和有过自杀行为的人深交|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||在整个自杀事件中，最痛苦的是自杀者的家属|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||对于身患绝症又极度痛苦的病人，可由医务人员在法律的支持下帮助病人结束生命|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||在处理自杀事件过程中，应该对其家属表示同情和关心，并尽可能为他们提供帮助|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||自杀是对人生命尊严的践踏|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||不应为自杀死亡者开追悼会|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||如果我的朋友自杀未遂，我会比以前更关心他|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||如果我的邻居家里有人自杀，我会逐渐疏远和他们的关系|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||安乐死是对人生命尊严的践踏|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||自杀是对家庭和社会一种不负责任的行为|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||人们不应该对自杀死亡者评头论足|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||我对那些反复自杀者很反感，因为他们常常将自杀作为控制别人的一种手段|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||对于自杀，自杀者的家属在不同程度上都应负有一定的责任|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||假如我自己身患绝症又处于极度痛苦之中，我希望医务人员能帮助我结束自己的生命|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||个体为某种伟大的，超过人生命价值的目的而自杀是值得赞许的|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||一般情况下，我不愿去看望自杀未遂者，即使是亲人或好朋友也不例外|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||自杀只是一种生命现象，无所谓道德上的好与坏|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||自杀未遂者不值得同情|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||对于身患绝症又极度痛苦的人，可不再为其进行维持生命的治疗|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||自杀是对亲人和朋友的背叛|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||人有时为了尊严和荣誉不得不自杀|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||在交友时，我不太注意对方是否有过自杀行为|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||对自杀未遂者应给予更多的关心与帮助|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||当生命已无欢乐可言时，自杀是可以理解的|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||假如我身患绝症又处于极度痛苦之中，我不愿再接受维持生命的治疗|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5",
            "自杀态度问卷（SAQ）||一般情况下，我不会和家中有过自杀者的人结婚|完全赞同;赞同;中立;不赞同;完全不赞同|5;4;3;2;1",
            "自杀态度问卷（SAQ）||人应有选择自杀的权利|完全赞同;赞同;中立;不赞同;完全不赞同|1;2;3;4;5", };
    // <2.5分被认为对自杀持肯定、认可、理解和宽容的态度；2.5～3.5分为矛盾或中立态度；≥3.5分被认为对自杀持反对、否定、排斥和歧视态度。
    public static final String[][] ZS_CSJG = new String[][] {
            { "1", "2.5", "您对自杀持肯定、认可、理解和宽容的态度", "暂无分析说明", "暂无建议" },
            { "2.5", "3.5", "您对自杀持矛盾或中立态度", "暂无分析说明", "暂无建议" },
            { "3.5", "5", "满意", "暂无分析说明", "暂无建议" }, };

    // UCLA孤独量表
    public static final String[] UCLA_CONTENT = new String[] {
            "UCLA孤独量表||我感觉自己与周围人的相处和谐。|从不;很少;有时;一直|4;3;2;1",
            "UCLA孤独量表||我感觉自己缺少别人的友情。|从不;很少;有时;一直|1;2;3;4",
            "UCLA孤独量表||我的周围没有谁能让我可以向他（她）寻求帮助。|从不;很少;有时;一直|1;2;3;4",
            "UCLA孤独量表||我不感到寂寞。|从不;很少;有时;一直|4;3;2;1", "UCLA孤独量表||我觉得自己是同伴中的一员。|从不;很少;有时;一直|4;3;2;1",
            "UCLA孤独量表||我想我和周围人有许多共同之处。|从不;很少;有时;一直|4;3;2;1",
            "UCLA孤独量表||我情趣不能被周围人所共享。|从不;很少;有时;一直|1;2;3;4",
            "UCLA孤独量表||我是一个容易与人交往的人|从不;很少;有时;一直|4;3;2;1",
            "UCLA孤独量表||周围有让我感到亲近的人。|从不;很少;有时;一直|4;3;2;1",
            "UCLA孤独量表||我有被别人遗忘的感觉。|从不;很少;有时;一直|4;3;2;1",
            "UCLA孤独量表||我觉得我和周围人关系淡漠。|从不;很少;有时;一直|1;2;3;4",
            "UCLA孤独量表||我感到没有人能真正理解我。|从不;很少;有时;一直|1;2;3;4",
            "UCLA孤独量表||我觉得自己很孤立。|从不;很少;有时;一直|1;2;3;4",
            "UCLA孤独量表||需要时，我能找到人陪伴我。|从不;很少;有时;一直|4;3;2;1",
            "UCLA孤独量表||我能找到可与其谈心的人。|从不;很少;有时;一直|4;3;2;1",
            "UCLA孤独量表||在我周围，有我能向其寻求帮助的人。|从不;很少;有时;一直|4;3;2;1",
            "UCLA孤独量表||在我感觉自己和周围人缺少共同语言。|从不;很少;有时;一直|1;2;3;4",
            "UCLA孤独量表||我觉得有人关心我。|从不;很少;有时;一直|4;3;2;1" };
    public static final String[][] UCLA_CSJG = new String[][] {
            { "20", "39", "孤独感弱", "与周围人，朋友、亲人相处，情感上能得到满足，平时不感到孤独，或很少有孤独感",
                    "被试参与人际交往的频度和质量与其自己的社交要求相近。与周围人，朋友、亲人相处，情感上能得到满足，平时不感到孤独，或很少有孤独感。" },
            {
                    "39",
                    "50",
                    "孤独感强",
                    "有中度的孤独感，经常为此苦恼。",
                    "被试经常感到自己的好朋友很少，没有人能真正了解自己，平时伙伴比较少，可相互交流的对象也很少。有时感到被人冷落，很寂寞，有时又感到得不到朋友的关心，难以融入周围的社交圈，被试经常为此苦恼，情感上存在中度的孤独感。" },
            {
                    "50",
                    "800",
                    "孤独感很强",
                    "有严重的孤独感。",
                    "被试表现出严重的孤独感。常常既感到非常寂寞，又采取与人疏远的行动。对人际交往的态度消极，频度低。认为与人交往无意义，几乎没有好友，或亲密关系者。平时独处，孤立的时间相当多，常常伴有被抛弃感，抑郁或等不良心理，情感和精神都感到痛苦。" } };

    // "舒适状况量表(GCQ)
    public static final String[] SS_CONTENT = new String[] {
            "舒适状况量表（GCQ）||当我需要帮助时，我可以找到可靠的人。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我不想活动。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我的状况使我很沮丧。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我感觉有信心。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我现在觉得生命很有价值。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||知道别人在关心我，我很受鼓舞。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||太吵，我不能休息。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||没有人能体会我现在的感受。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我疼痛得不能忍受。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||没人陪伴时我很不开心。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我不喜欢这里这样。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我现在情绪低落。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我现在觉得身体不好。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||这个房间让我感觉怪怪的。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我害怕将会发生的事情。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我现在非常累。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||咳嗽时疼痛难以忍受。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我现在感到很满足。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||这床铺（椅子）让我不舒服。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||这里气氛很平静。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||这里没有我喜欢的东西。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||在这里我没有归属感。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我亲戚、朋友经常打电话来关心我。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我需要更好地了解我的病情。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||医护人员不关心我的感受。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我没有太多的选择。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||这房间气味不好。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我心情很平静。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||我发现生活很有意义。|非常不同意;不同意;同意;非常同意|1;2;3;4",
            "舒适状况量表（GCQ）||希望家属多陪伴我。|非常不同意;不同意;同意;非常同意|1;2;3;4", };
    public static final String[][] SS_CSJG = new String[][] {
            { "30", "60", "低度舒适", "暂无分析说明", "你的生理和心理状态较差，社交和生活环境处于一般，请尽快努力提高生活舒适度。" },
            { "60", "90", "中度舒适", "暂无分析说明", "你的生理和心理状态一般，社交和生活环境处于中等，请努力提高生活舒适度。" },
            { "90", "120", "高度舒适", "暂无分析说明", "你的生理和心理状态很好，社交和生活环境非常如意，请继续保持。" }, };

    /**
     * 90项症状清单（SCL-90）
     */
    public static final String[] SCL90_CONTENT = new String[] {
            "90项症状清单（SCL-90）|您是否|头痛 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|神经过敏，心中不踏实。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|头脑中有不必要的想法或字句盘旋。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|头晕或昏倒。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|对异性的兴趣减退。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|对旁人责备求全。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到别人能控制您的思想。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|责备别人制造麻烦。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|忘性大。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|担心自己的衣饰的的整齐及仪态的端正。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",

            "90项症状清单（SCL-90）|您是否|容易烦恼和激动。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|胸痛。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|害怕空旷的场所或街道。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到自己的精力下降，活动减慢。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|想结束自己的生命。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|听到旁人听不到的声音。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|发抖。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到大多数不可信任。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|胃口不好。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|容易哭泣。|没有;很轻;中等;偏重;严重|1;2;3;4;5",

            "90项症状清单（SCL-90）|您是否|同异性相处时感到害羞不自在。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到受骗，中了圈套或有人想抓住您。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|无缘无故的突然感到害怕。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|自己不能控制的在发脾气。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|怕单独出门。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|经常责怪自己。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|腰痛。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到难以完成任务。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到孤独。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到苦闷。|没有;很轻;中等;偏重;严重|1;2;3;4;5",

            "90项症状清单（SCL-90）|您是否|过分担忧。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|对事物不敢兴趣。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到害怕。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|我的感情容易受到伤害。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|旁人能知道您的私下想法。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到别人不理解您，不同情您。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到人们对您不友好，不喜欢您。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|做事必须做的很慢以保证做的正确。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|心跳的很厉害。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|恶心或胃部不舒服。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",

            "90项症状清单（SCL-90）|您是否|感到比不上他人。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|肌肉酸痛。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到有人监视您谈论您。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|难以入睡。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|做事必须反复检查。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|难以做出决定。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|怕乘电车、公共汽车、地铁或火车。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|呼吸有困难。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|一阵阵发冷或发热。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|因为感到害怕而避开了某些东西、场合或活动。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",

            "90项症状清单（SCL-90）|您是否|脑子变空了。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|身体发麻或刺痛。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|喉咙有梗塞感。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到没有前途，没有希望。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|不能集中注意。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到身体某一部分软弱无力。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到紧张或容易紧张。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到手或脚发重。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|想到死亡的事。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|吃得太多。|没有;很轻;中等;偏重;严重|1;2;3;4;5",

            "90项症状清单（SCL-90）|您是否|当别人看着您或谈论您时感到不自在。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|有些不属于您自己的看法。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|有想打人或伤害他人的冲动。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|醒得太早。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|必须反复洗手、点数目或触摸某些东西。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|睡的不稳不深。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|有想摔坏或破坏东西的冲动。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|有一些别人没有的想法或念头。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到对别人神经过敏。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|在商店或电影院等人多的地方感到不自在。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",

            "90项症状清单（SCL-90）|您是否|感到任何事情都很困难。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|一阵阵恐惧或惊恐。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到在公共场合吃东西很不舒服。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|经常与人争论。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|单独一人时神经很紧张。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|别人对您的成绩没有做出恰当的评价。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|即使和别人在一起也感到孤独。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到坐立不安，心神不定。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到自己没有什么价值。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到熟悉东西变得陌生或不像是真的。|没有;很轻;中等;偏重;严重|1;2;3;4;5",

            "90项症状清单（SCL-90）|您是否|大叫或摔东西。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|害怕会在公共场合晕倒。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到别人想占您的便宜。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|为一些有关“性”的想法而很烦恼。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|您认为应该为自己的过错而受到惩罚。 |没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到要赶快把事情做好。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到自己的身体有严重问题。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|从未感到和其他人很亲近。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到自己有罪。|没有;很轻;中等;偏重;严重|1;2;3;4;5",
            "90项症状清单（SCL-90）|您是否|感到自己的脑子有毛病。 |没有;很轻;中等;偏重;严重|1;2;3;4;5", };

    /**
     * 90项症状结论（SCL-90）
     */
    public static final String[][] SCL90_CSJG = new String[][] {
            { 
                "躯体化：你的身心健康状况良好，请继续保持！",
                "躯体化：有时感到头晕或头部不适感，如紧绷、胀等。偶而会有胃部不适、胀气、嗳 气，有时出现心跳加快、心慌，有时常有疲倦的感觉。",
                "躯体化：经常有躯体不适的感觉，常有头晕、头胀、紧绷等症状，胃部不适较多，食欲不振、嗳气、胃胀，经常有心慌、心跳快，有时感到胸闷、呼吸急促，常有疲乏、虚弱感觉。" ,
                "躯体化：躯体不适明显，如胃纳差，腹胀等，且常伴有烦躁不安的情绪；经常感到头部不适，紧绷，头晕，头胀，或胸闷气急，心慌，有时担心自己会突然死去。",
                "躯体化：躯体不适感受非常明显，伴有烦躁不安的情绪，总是感到头部不适、紧绷、头晕、头胀，或是胸闷气急，犹如石头压住的感觉，心慌多，常担心会突然死亡，胃纳差、腹胀。",
            },
            { 
                "强迫症状：你的身心健康状况良好，请继续保持！",
                "强迫症状：有时会表现为担心、害怕而需要反复去思考某些念头或想法。有时表现为出现反复的重复做动作，如洗手或一些特殊的动作，但时间较短，可以有掩饰的行为 ，虽有些痛苦，但尚能工作。",
                "强迫症状：反复的重复做的动作明显增多，或反复思考一些想法，感到没有必要，想作抵抗，但无法克制内心的冲动，只能重复，伴有较明显焦虑，有一定求医行为。一天中有34小时会做反复动作或竭力思考等强迫行为。",
                "强迫症状：每天有4～6小时重复某些动作或反复思考某些想法，甚至影响到日常生活，使其社会功能和生活功能部分受损，患者明知这些动作和思虑没有必要，但无法控制，极其焦虑，不得不以重复的的动作和反复的思虑来换取暂时的心理轻松，但过后又为焦虑所支配去重复动作和思虑。",
                "强迫症状：每天有6～8小时去做些反复的动作或思考某些想法，已经明显的影响到社会功能和生活功能。患者明知没有必要，但无法抗拒，而内心十分痛苦。每次完成之后，心里有轻松感或舒适的感觉，但不多久，又被焦虑、担心所支配，再次做重复的动作。",
            },
            { 
                "人际关系敏感：你的身心健康状况良好，请继续保持！",
                "人际关系敏感：性格基础是内向、敏感、胆小且易害羞。在陌生人面前不多开口讲话，显得害羞。心理较脆弱，有时会产生别人对自己不理解而感到痛苦。" ,
                "人际关系敏感：很少在生人面前多讲话，表现为敏感、多疑且易害羞。对别人讲话比较留心，常担心别人会议论自己，有时对别人有警惕心理状态，做事小心谨慎。",
                "人际关系敏感：内向，敏感，不大主动与人交谈，但对别人的评价非常在意，有时显得神 经过敏，偶尔会表现出被动攻击，影响其人际关系，使之朋友较少。",
                "人际关系敏感：平时很内向，常因过分敏感而少与人讲话，而对别人的谈论经常加倍注意，担心有人议论而显得神经过敏状态，并有时会采取指责别人的方法，而使得朋友极少。",
            },
            {
                "抑郁：你的身心健康状况良好，请继续保持！", 
                "抑郁：自我评价稍低，平时落落寡欢、心情欠佳、兴趣也有些下降，偶而会心情烦躁不愿意与人交往，有时夜眠差些、多梦。",
                "抑郁：自我评价比较低，情绪低落明显，常有愁眉苦脸状态，兴趣也减退。有时会哭泣或感到活着太累的想法，失眠常见，食欲不振",
                "抑郁：自我评价低，情绪低落，悲观，失望，有时控制不住要哭泣，甚至出现厌世的消极念头，有明显的抑郁体征如失眠早醒，食欲减退，体重下降等。",
                "抑郁：自我评价很低，常感到自己象无用的人，情绪低落明显，整日愁眉不展，自己责怪自己。有时会产生无助感或绝望感，有消极观念出现，食欲减退，饮食不振。",
            },
            { 
                "焦虑：你的身心健康状况良好，请继续保持！", 
                "焦虑：经常有莫名的担心，有时会有害怕的感觉，但有时不明白为什么会这样。可伴有心慌、心跳快的感受，易紧张，偶可见出汗，也会采取一些掩饰举动。",
                "焦虑：经常有不明所以的担心、紧张，无法掩饰的焦虑状态，如心跳、心慌，常有出 汗，手指轻微的颤抖，有时出现坐立不安的现象。",
                "焦虑：坐立不安，经常显得紧张，担心将临灭顶之灾而出现明显的焦虑体征，如胸闷 、气急、心跳快、心慌，混身发抖，四肢出汗，尿频等。",
                "焦虑：明显的紧张不安，总感到有不好的事要发生，坐立不安，来回徘徊。经常大汗淋漓，心跳快、心慌、胸闷气急，严重的惊恐发作时，可有疑死的感觉而非常紧张。",
            }, 
            { 
                "敌对：你的身心健康状况良好，请继续保持！", 
                "敌对：偶有怀疑的想法，感到别人不是真心的友好，经常表现警惕性增高，注意别人的举动，是否对自己友好，偶而发脾气。" ,
                "敌对：平时警惕性就比较高，经常注意或怀疑别人的一举一动，谈话议论都要加以注意，是否有不利于自己的举动，常与人难以相处，偶然有破坏性行为如以摔东西来发泄对别人的不满。",
                "敌对：平时充满敌意，经常疑心别人有意与他作对，内心对环境忿忿不满，常常有想 叫骂，摔东西，还常为小事与人争斗，终使之难以与周围人相处。",
                "敌对：怀疑心十分明显，甚至会怀疑别人采取了迫害自己的事。经常会发怒，激惹性增强，以叫骂，摔东西来发泄，与人难以相处，甚至连亲人都无法友好相处。",
            }, 
            { 
                "恐怖：你的身心健康状况良好，请继续保持！", 
                "恐怖：有时不敢独自一人留在家中，与他人相处有一种安全感，有的表现为不敢去空旷处，有的则不敢与生人交往，但能够尽力的克制或掩饰自己，对工作影响不明显",
                "恐怖：经常会采取某些逃避行为，主要是担心、缺少安全感，所以表现为不敢独自一人留在家中，不敢去空旷的地方，强制去时有紧张、焦虑、手足出汗的症状出现，也会有心跳加快、心慌的表现。",
                "恐怖：无法接触恐怖的对象，经常采取主动逃避行为，有焦虑，紧张，心慌的感觉。一旦接触到恐怖对象会引起惊恐发作和濒死感。已影响到日常工作和生活。",
                "恐怖：对自己所恐怖的对象无法去接触，会采取强烈的逃避行为，伴有紧张、焦虑的 心情，严重时会产生昏厥的担心，甚至有胸闷气急或疑死感受，明显的影响工作和日常生活。",
            }, 
            { 
                "偏执：你的身心健康状况良好，请继续保持！", 
                "偏执：性格固执，即使是错误的观点，亦不大能改变。有时会有敌对性，经常有怀疑心，而无法相信别人，偶而认为会被人跟踪。" ,
                "偏执：怀疑性明显，经常认为别人和自己过不去，要迫害他，对别人的一举一动也认为与自己有关系，常感到被人跟踪或被人监视。",
                "偏执：不信任别人，经常怀疑别人捉弄他，迫害他，为此去收集别人加害了他的依据 ，如走在路上感到被跟踪，看到报导认为在含沙射影针对他等，内心紧张，害怕，忿忿不平，经常影响工作与生活。",
                "偏执：无法信任别人，经常怀疑别人要阴谋迫害他，认为是受到监视，被人跟踪，经常会采取逃避的行为，或者紧张、害怕、不敢出门，无法去工作，日常生活也受到影响。",
            }, 
            { 
                "精神病性：你的身心健康状况良好，请继续保持！", 
                "精神病性：偶而有幻听，大多是被人批评、责怪，有时认为是别人迫害他的行为，偶而会出现自己脑子里想的东西没有讲出来，别人就知道了，常有敌对性。可能会自责 倾向，还可能有孤独感。",
                "精神病性：经常有幻听，内容多为批评、责骂，有时与幻听互相对夸、对骂，认为别人要害他，跟踪他，伴有紧张情绪，有时认为自己的思维被别人知道了，还会播散出去。或有些自责倾向，感到孤独。",
                "精神病性：经常有幻听，内容多为批评责骂，且把幻觉当作真实，有时与之对骂，有时为之受惊吓，有时听从幻听去伤人，自残和破坏环境，有时则显得淡漠，发呆。或 经常自责，难以摆脱孤独感。",
                "精神病性：经常有幻听，其内容多为批评、责骂，少数为表扬，有时沉浸在幻听中，而不与人接触交谈，且伴有明显的独自发呆，喃喃自语，或与幻听对骂，有时感到思维被别人了解了，或被人控制了，而没有行动的自由了。有时在命令性幻听的支配下做出伤人或自伤的举动。或有严重自责和孤独感。",
            },
    };

    /**
     * 儿童少年生活质量量表数据
     */
    public static final String[] QLSCA_CONTENT = new String[] {
            "儿童少年生活质量量表（QLSCA）||你觉得班上同学对你友好吗？|不友好;有一点友好;比较友好;非常友好|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你能轻松的参加田径和球类运动吗？|不轻松;较轻松但体力较差;较轻松但体力较好;非常轻松|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你对自己的记忆力感到满意吗？|非常不满意;有一点不满意;比较满意;非常满意|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你家附近有你可以进行体育活动的场所吗？|没有;有但不安全 ;有但比较小;有很好的场所|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你对你和老师的关系感到满意吗？|不满意;有一点不满意;比较满意;非常满意|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你对父母和你的关系感到满意吗？|不满意;有一点不满意;比较满意;非常满意|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你有机会参加你喜欢的课余活动吗？|很少有机会;机会较少;有比较多的机会;有非常多的机会|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你的朋友关心你吗？|不关心;有一点关心;比较关心;非常关心|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你的朋友多吗？|有一二个;有三四个;有五六个;有很多个|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你对自己参加体育活动的能力感到满意吗？|不满意;有一点不满意;比较满意;非常满意|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你家周围的环境安静吗？|非常吵闹;有一点吵闹;比较安静;非常安静|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你对自己的睡眠情况感到满意吗？|非常不满意;有一点不满意;比较满意;非常满意|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你对自己的精力感到满意吗？|非常不满意;有一点不满意;比较满意;非常满意|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你对自己的生活感到满意的吗？|非常不满意;有一点不满意;比较满意;非常满意|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你家附近方便地买到你的生活主学习用品吗？|很不方便;有一点不方便;比较方便;非常方便|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你觉得老师喜欢你吗？|不喜欢;有一点不喜欢;比较喜欢;非常喜欢|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你对自己的健康感到满意吗？|非常不满意;有一点不满意;比较满意;非常满意|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你家附近交通方便吗？|很不方便;有一点不方便;比较方便;非常方便|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你对自己的朋友感到满意吗？|非常不满意;有一点不满意;比较满意;非常满意|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你认为自己的生活快乐吗？|非常不快乐;有一点不快乐;比较快乐;非常快乐|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常为已经做过的事后悔吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常觉得大多数人很喜欢你吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你一做作业就心烦吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你做作业的过程中常需要休息好几次吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||当遇到难题时，你仍然能坚持做下去吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||当遇到困难时，你能得到老师的帮助吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你偏爱某些食物或不吃某些食物吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你容易感到紧张或害怕吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||需要帮助时，你能找到可信赖的的朋友吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常很容易记住新学的知识吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常感到疼痛或身体不舒服吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常为这样或那样的事感到烦恼吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常有机会去看展览、比赛或旅游吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你除了完成作业还愿意做其他的练习吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常担心自己做错事吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你觉的自己是集体中重要的人吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常喜欢和父母呆在一起吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常举手回答老师提出的问题吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常感到累或者没有精神吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你早上起床后常觉得还是很累吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你喜欢你的老师吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你认为自己是个好学生吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常觉得生活没意思吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||父母能理解你的想法吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常要花很长的时间来完成作业吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你觉的老师对你友好吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常觉的不想吃饭或吃得过饱吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||你常参加体育锻炼吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4",
            "儿童少年生活质量量表（QLSCA）||当遇到困难时,你愿意告诉父母吗？|从不这样;很少这样;经常这样;总是这样|1;2;3;4", };
    /**
     * 儿童少年生活质量量表诊断结论
     */
    public static final String[][] QLSCA_CSJG = new String[][] {
            {
                    "0",
                    "30",
                    "生活质量差",
                    "暂无分析说明",
                    "你的生活质量差，请及时调整生活状态，乐观积极向上，多与父母，老师沟通问题，不要将问题积压在心里，长此以往会造成严重的心里负担，失去对学习的信心；应当树立起良好的生活态度，与同学之间保持良好的友谊，加强体育锻炼，按时完成作业，父母老师要起到监督的作用；父母可以在孩子取得好的成绩的时候给予一定的奖励，买个小玩具或带着孩子出去游玩下，适当的娱乐会使孩子生活学习的更愉悦！" },
            { "30", "60", "生活质量一般", "暂无分析说明",
                    "你的生活质量一般，请继续努力，争取做到更好，请加强与老师，同学关系，增强学习能力和生活处事态度，从生活细节一步一步提高！相信自己，通过努力你一定会到达理想的生活状态！" },
            { "60", "70", "生活质量较好", "暂无分析说明", "你的生活质量较好，可以做的更好，好好努力哦！" },
            { "70", "100", "生活质量非常好", "暂无分析说明", "请继续保持，再接再厉！" }, };

    /**
     * 儿童少年生活质量量判断标准 1维代表男女 2维代表岁数，从7-18 3维代表数据 M和SD
     */
    public static final double[][][] QLSCA_STANDARD = new double[][][] {
            { { 151.97, 18.08 }, { 146.59, 18.27 }, { 144.90, 17.41 }, { 144.94, 16.85 },
                    { 145.99, 16.44 }, { 145.65, 16.69 }, { 141.86, 17.54 }, { 137.26, 15.48 },
                    { 136.27, 15.85 }, { 134.24, 16.93 }, { 131.61, 15.72 }, { 129.58, 14.69 } },
            { { 150.61, 18.29 }, { 150.18, 16.60 }, { 148.99, 17.94 }, { 147.22, 17.47 },
                    { 147.79, 17.26 }, { 145.74, 17.23 }, { 140.47, 16.87 }, { 136.12, 14.55 },
                    { 134.81, 15.83 }, { 130.86, 15.52 }, { 130.74, 16.03 }, { 129.21, 15.67 } } };

    public static final String[] CSMCS = new String[] { "抑郁自评量表(SDS)", // 抑郁自评量表
            "焦虑自评量表(SAS)",// 焦虑自评量表
            "匹兹堡睡眠质量指数(PSQI)",// 医院焦虑抑郁量表(HAD)
            "自杀态度问卷(SAQ)",// 抑郁体验问卷(DEQ)
            "UCLA孤独量表",// 流调用抑郁自评量表(CES-D)
            "舒适状况量表(GCQ)",// 贝克抑郁自评量表(BDI)
            "90项症状清单（SCL-90）", "儿童少年生活质量量表（QLSCA）" };

    public static final String[][] CSLRS = new String[][] { YY_CONTENT, JL_CONTENT, PZ_CONTENT,
            ZS_CONTENT, UCLA_CONTENT, SS_CONTENT, SCL90_CONTENT, QLSCA_CONTENT };

    public static final String[][][] CSJGS = new String[][][] { YY_CSJG, JL_CSJG, PZ_CSJG, ZS_CSJG,
            UCLA_CSJG, SS_CSJG, SCL90_CSJG, QLSCA_CSJG };
}
