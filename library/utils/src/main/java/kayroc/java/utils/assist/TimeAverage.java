package kayroc.java.utils.assist;

/**
 * 时间均值计算辅助类
 *
 * @author kayroc
 */
@SuppressWarnings("unused")
public final class TimeAverage {

    /** 计时器 */
    private final TimeCounter mTimeCounter = new TimeCounter();
    /** 均值器 */
    private final Average mAverage = new Average();

    /**
     * 开始计时 ( 毫秒 )
     *
     * @return 开始时间 ( 毫秒 )
     */
    public long start() {
        return mTimeCounter.start();
    }

    /**
     * 结束计时 ( 毫秒 )
     *
     * @return 结束时间 ( 毫秒 )
     */
    public long end() {
        long time = mTimeCounter.duration();
        mAverage.add(time);
        return time;
    }

    /**
     * 结束计时, 并重新启动新的计时
     *
     * @return 距离上次计时的时间差 ( 毫秒 )
     */
    public long endAndRestart() {
        long time = mTimeCounter.durationRestart();
        mAverage.add(time);
        return time;
    }

    /**
     * 求全部计时均值
     *
     * @return 全部计时时间均值
     */
    public Number average() {
        return mAverage.getAverage();
    }

    /**
     * 输出全部时间值
     *
     * @return 计时信息
     */
    public String print() {
        return mAverage.print();
    }

    /**
     * 清除计时数据
     *
     * @return {@link TimeAverage}
     */
    public TimeAverage clear() {
        mAverage.clear();
        return this;
    }
}