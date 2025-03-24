# SpringBootVueGaoKao
基于JAVA SpringBoot和Vue高考志愿填报辅助系统

版本1演示视频链接：https://www.bilibili.com/video/BV1wh4y157pE/

版本2演示视频链接：https://www.bilibili.com/video/BV1JZ42187LY/

版本2演示视频链接：https://www.bilibili.com/video/BV12corYkEHP/

版本1实现的功能：

本系统的功能包括：系统注册和登录、学校推荐、职业推荐、职业特点测评、高考志愿评估等功能。

注册、登录：未注册用户可以注册，有了账号后可以登录网站；

学校推荐：输入高考分数，系统给出符合条件的也就是该分数所能报考的院校信息。（有排序，如：按照分数与院校最低录取分数线之间的差距做一个排序）；

职业推荐：输入各科成绩（根据考生强项给出推荐专业）；

职业特点测评：提供MBIT测试，做测试之后给出类型、推荐职业、该职业所对应的专业信息；

高考志愿评估：输入考生已填写的志愿信息，系统做出一个综合评估，给出意见。（如：考生报考一个学校能被录取的可能性建议。）

版本2在版本1的基础上新增功能如下：

学校管理：管理员可以对学校信息进行管理；

专业管理：管理员可以对专业信息进行管理；

志愿管理：管理员可以对志愿信息进行管理，学生用户可以选择填报志愿，并由管理员审核；

用户管理：管理员可以对学生用户的信息进行管理；

版本3重构了整个项目，具体功能如下：

学生用户拥有的功能如下：

注册、登录：未注册用户可以注册，有了账号后可以登录网站；

学校推荐：输入高考分数，系统给出符合条件的也就是该分数所能报考的院校信息。（使用加权平均算法）；

专业推荐：输入各科成绩（根据考生强项给出推荐专业）；

职业专业特点测评：提供MBIT测试，做测试之后给出类型、推荐职业、该职业所对应的专业信息；

高考志愿评估：输入考生已填写的志愿信息，系统做出一个综合评估，给出意见。（如：考生报考一个学校能被录取的可能性建议，使用豆包大模型的API。）

管理员拥有以下功能：

学校管理：管理员可以对学校信息进行管理；

专业管理：管理员可以对专业信息进行管理；

志愿管理：管理员可以对志愿信息进行管理，学生用户可以选择填报志愿，并由管理员审核；

用户管理：管理员可以对学生用户的信息进行管理；


用到的技术：

后端：java语言，SpringBoot框架，MySQL数据库，Maven依赖管理等；

前端：vue等。

详询 微信1：egvh56ufy7hh ，微信2：dabocode  。承接商业项目、课设、毕设和论文，包括但不限于Web、APP、小程序等，课设、毕设提供远程部署和不限次数代码解答！
