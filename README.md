# CountDownTask
用于android平台倒计时的一个项目
项目维护一个任务列表，开启一个timer，每秒循环一次任务列表
# 每个任务有两个关键字段，周期period，时长duration
# 基础类 TimeHelper
  TimeHelper.init();//项目初始化
  TimeHelper.release();//项目释放
  
  TimeHelper.getInstances().startTimer();//倒计时任务开始
  TimeHelper.getInstances().stopTimer();//倒计时任务停止
  
  //添加任务
  TimeHelper.getInstances().putTask(uuid.toString(), new ICountDownTask(period)) {
            @Override
            public void onRefresh() {
                //按周期刷新
                updateCountTime();
            }

            @Override
            public void onFinished() {
                //任务周期停止
                finishCountTime();
            }

            @Override
            public void onCancle() {

            }
        }, duration);
   //移除任务
   TimeHelper.getInstances().removeTask(uuid.toString());
   
#maven 引用
<xml>
<dependency>
  <groupId>com.sel.countdown</groupId>
  <artifactId>count_down</artifactId>
  <version>0.0.2</version>
  <type>pom</type>
</dependency>
</xml>
#gradle引用
compile 'com.sel.countdown:count_down:0.0.2'
