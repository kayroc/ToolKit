package kayroc.java.utils.assist.record;

/**
 * 日志记录插入信息
 *
 * @author kayroc
 */
@SuppressWarnings("unused")
public final class RecordInsert {

    /** 文件信息 ( 一个文件只会添加一次文件信息, 且在最顶部 ) */
    protected String mFileInfo;
    /** 每条日志头部信息 */
    protected String mLogHeader;
    /** 每条日志尾部信息 */
    protected String mLogTail;

    // ==========
    // = 构造函数 =
    // ==========

    public RecordInsert(final String fileInfo) {
        this.mFileInfo = fileInfo;
    }

    public RecordInsert(
        final String fileInfo,
        final String logHeader,
        final String logTail
    ) {
        this.mFileInfo = fileInfo;
        this.mLogHeader = logHeader;
        this.mLogTail = logTail;
    }

    // ===========
    // = get/set =
    // ===========

    public String getFileInfo() {
        return mFileInfo;
    }

    public RecordInsert setFileInfo(final String fileInfo) {
        this.mFileInfo = fileInfo;
        return this;
    }

    public String getLogHeader() {
        return mLogHeader;
    }

    public RecordInsert setLogHeader(final String logHeader) {
        this.mLogHeader = logHeader;
        return this;
    }

    public String getLogTail() {
        return mLogTail;
    }

    public RecordInsert setLogTail(final String logTail) {
        this.mLogTail = logTail;
        return this;
    }
}