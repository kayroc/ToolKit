package kayroc.java.utils;


import android.os.Build;
import android.text.Html;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import kayroc.java.utils.cipher.Base64;

/**
 * 编码工具类
 *
 * @author kayroc
 * <pre>
 *     Base64 flags
 *     <p></p>
 *     CRLF 这个参数看起来比较眼熟, 它就是 Win 风格的换行符, 意思就是使用 CR LF 这一对作为一行的结尾而不是 Unix 风格的 LF
 *     DEFAULT 这个参数是默认, 使用默认的方法来加密
 *     NO_PADDING 这个参数是略去加密字符串最后的 =
 *     NO_WRAP 这个参数意思是略去所有的换行符 ( 设置后 CRLF 就没用了 )
 *     URL_SAFE 这个参数意思是加密时不使用对 URL 和文件名有特殊意义的字符来作为加密字符, 具体就是以 - 和 _ 取代 + 和 /
 * </pre>
 */
@SuppressWarnings("unused")
public final class EncodeUtils {

    private EncodeUtils() {
    }

    // ==============
    // = URL 编码 =
    // ==============

    /**
     * URL 编码
     *
     * @param input 待处理数据
     *
     * @return URL 编码后的字符串
     */
    public static String urlEncode(final String input) {
        return urlEncode(input, "UTF-8");
    }

    /**
     * URL 编码
     *
     * @param input       待处理数据
     * @param charsetName 字符集
     *
     * @return URL 编码后的字符串
     */
    public static String urlEncode(
        final String input,
        final String charsetName
    ) {
        if (input == null || input.length() == 0) {
            return "";
        }
        try {
            return URLEncoder.encode(input, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    // ===========
    // = URL 解码 =
    // ===========

    /**
     * URL 解码
     *
     * @param input 待处理数据
     *
     * @return URL 解码后的字符串
     */
    public static String urlDecode(final String input) {
        return urlDecode(input, "UTF-8");
    }

    /**
     * URL 解码
     *
     * @param input       待处理数据
     * @param charsetName 字符集
     *
     * @return URL 解码后的字符串
     */
    public static String urlDecode(
        final String input,
        final String charsetName
    ) {
        if (input == null || input.length() == 0) {
            return "";
        }
        try {
            String safeInput = input.replaceAll("%(?![0-9a-fA-F]{2})", "%25").replaceAll("\\+", "%2B");
            return URLDecoder.decode(safeInput, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    // ==============
    // = Base64 编码 =
    // ==============

    /**
     * Base64 编码
     *
     * @param input 待处理数据
     *
     * @return Base64 编码后的 byte[]
     */
    public static byte[] base64Encode(final String input) {
        return base64Encode(input, Base64.NO_WRAP);
    }

    /**
     * Base64 编码
     *
     * @param input 待处理数据
     * @param flags Base64 编解码 flags
     *
     * @return Base64 编码后的 byte[]
     */
    public static byte[] base64Encode(
        final String input,
        final int flags
    ) {
        return base64Encode(ConvertUtils.toBytes(input), flags);
    }

    /**
     * Base64 编码
     *
     * @param input 待处理数据
     *
     * @return Base64 编码后的 byte[]
     */
    public static byte[] base64Encode(final byte[] input) {
        return base64Encode(input, Base64.NO_WRAP);
    }

    /**
     * Base64 编码
     *
     * @param input 待处理数据
     * @param flags Base64 编解码 flags
     *
     * @return Base64 编码后的 byte[]
     */
    public static byte[] base64Encode(
        final byte[] input,
        final int flags
    ) {
        if (input == null) {
            return null;
        }
        return Base64.encode(input, flags);
    }

    /**
     * Base64 编码
     *
     * @param input 待处理数据
     *
     * @return Base64 编码后的 byte[] 转 String
     */
    public static String base64EncodeToString(final String input) {
        return base64EncodeToString(input, Base64.NO_WRAP);
    }

    /**
     * Base64 编码
     *
     * @param input 待处理数据
     * @param flags Base64 编解码 flags
     *
     * @return Base64 编码后的 byte[] 转 String
     */
    public static String base64EncodeToString(
        final String input,
        final int flags
    ) {
        return base64EncodeToString(ConvertUtils.toBytes(input), flags);
    }

    /**
     * Base64 编码
     *
     * @param input 待处理数据
     *
     * @return Base64 编码后的 byte[] 转 String
     */
    public static String base64EncodeToString(final byte[] input) {
        return base64EncodeToString(input, Base64.NO_WRAP);
    }

    /**
     * Base64 编码
     *
     * @param input 待处理数据
     * @param flags Base64 编解码 flags
     *
     * @return Base64 编码后的 byte[] 转 String
     */
    public static String base64EncodeToString(
        final byte[] input,
        final int flags
    ) {
        if (input == null) {
            return null;
        }
        return ConvertUtils.newString(Base64.encode(input, flags));
    }

    // ==============
    // = Base64 解码 =
    // ==============

    /**
     * Base64 解码
     *
     * @param input 待处理数据
     *
     * @return Base64 解码后的 byte[]
     */
    public static byte[] base64Decode(final String input) {
        return base64Decode(input, Base64.NO_WRAP);
    }

    /**
     * Base64 解码
     *
     * @param input 待处理数据
     * @param flags Base64 编解码 flags
     *
     * @return Base64 解码后的 byte[]
     */
    public static byte[] base64Decode(
        final String input,
        final int flags
    ) {
        return base64Decode(ConvertUtils.toBytes(input), flags);
    }

    /**
     * Base64 解码
     *
     * @param input 待处理数据
     *
     * @return Base64 解码后的 byte[]
     */
    public static byte[] base64Decode(final byte[] input) {
        return base64Decode(input, Base64.NO_WRAP);
    }

    /**
     * Base64 解码
     *
     * @param input 待处理数据
     * @param flags Base64 编解码 flags
     *
     * @return Base64 解码后的 byte[]
     */
    public static byte[] base64Decode(
        final byte[] input,
        final int flags
    ) {
        if (input == null) {
            return null;
        }
        return Base64.decode(input, flags);
    }

    /**
     * Base64 解码
     *
     * @param input 待处理数据
     *
     * @return Base64 解码后的 byte[] 转 String
     */
    public static String base64DecodeToString(final String input) {
        return base64DecodeToString(input, Base64.NO_WRAP);
    }

    /**
     * Base64 解码
     *
     * @param input 待处理数据
     * @param flags Base64 编解码 flags
     *
     * @return Base64 解码后的 byte[] 转 String
     */
    public static String base64DecodeToString(
        final String input,
        final int flags
    ) {
        return base64DecodeToString(ConvertUtils.toBytes(input), flags);
    }

    /**
     * Base64 解码
     *
     * @param input 待处理数据
     *
     * @return Base64 解码后的 byte[] 转 String
     */
    public static String base64DecodeToString(final byte[] input) {
        return base64DecodeToString(input, Base64.NO_WRAP);
    }

    /**
     * Base64 解码
     *
     * @param input 待处理数据
     * @param flags Base64 编解码 flags
     *
     * @return Base64 解码后的 byte[] 转 String
     */
    public static String base64DecodeToString(
        final byte[] input,
        final int flags
    ) {
        if (input == null) {
            return null;
        }
        return ConvertUtils.newString(Base64.decode(input, flags));
    }

    // ============
    // = HTML 编码 =
    // ============

    /**
     * HTML 编码
     *
     * @param input 待处理数据
     *
     * @return HTML 编码后的字符串
     */
    public static String htmlEncode(final CharSequence input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0, len = input.length(); i < len; i++) {
            c = input.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '\'':
                    //http://www.w3.org/TR/xhtml1
                    // The named character reference &apos; (the apostrophe, U+0027) was
                    // introduced in XML 1.0 but does not appear in HTML. Authors should
                    // therefore use &#39; instead of &apos; to work as expected in HTML 4
                    // user agents.
                    sb.append("&#39;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    // ============
    // = HTML 解码 =
    // ============

    /**
     * HTML 解码
     *
     * @param input 待处理的数据
     *
     * @return HTML 解码后的字符串
     */
    @SuppressWarnings("deprecation")
    public static CharSequence htmlDecode(final String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(input);
        }
    }

    // ============
    // = 二进制编码 =
    // ============

    /**
     * 二进制编码
     *
     * @param input 待处理数据
     *
     * @return 二进制编码后的字符串
     */
    public static String binaryEncode(final String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char i : input.toCharArray()) {
            sb.append(Integer.toBinaryString(i)).append(" ");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    // ============
    // = 二进制解码 =
    // ============

    /**
     * 二进制解码
     *
     * @param input 待处理的数据
     *
     * @return 二进制解码后的字符串
     */
    public static String binaryDecode(final String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        String[] splits = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String split : splits) {
            sb.append(((char) Integer.parseInt(split, 2)));
        }
        return sb.toString();
    }
}