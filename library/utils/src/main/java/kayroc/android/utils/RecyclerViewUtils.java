package kayroc.android.utils;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import kayroc.android.utils.screenshot.CapturePictureUtils;

/**
 * RecyclerView 工具类
 *
 * @author kayroc
 * <pre>
 *     RecyclerView 截图使用 {@link CapturePictureUtils}
 *     RecyclerView 滑动使用 {@link ListViewUtils}
 *     <p></p>
 *     判断当前 view 是否在屏幕可见
 *     @see <a href="https://www.jianshu.com/p/85e8b9de5ecc"/>
 *     判断 RecyclerView 中 View 的可见性
 *     @see <a href="https://blog.csdn.net/xlh1191860939/article/details/113182209"/>
 * </pre>
 */
@SuppressWarnings("unused")
public final class RecyclerViewUtils {

    private RecyclerViewUtils() {
    }

    // ====================
    // = 获取 RecyclerView =
    // ====================

    /**
     * 获取 RecyclerView
     *
     * @param view {@link View}
     * @param <T>  泛型
     *
     * @return {@link RecyclerView}
     */
    @SuppressWarnings("unchecked")
    public static <T extends RecyclerView> T getRecyclerView(final View view) {
        if (view != null) {
            try {
                return (T) view;
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return null;
    }

    // ================
    // = LayoutParams =
    // ================

    /**
     * 获取 RecyclerView Item View LayoutParams
     *
     * @param itemView Item View
     *
     * @return Item View LayoutParams
     */
    public static RecyclerView.LayoutParams getLayoutParams(final View itemView) {
        if (itemView != null) {
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            if (layoutParams instanceof RecyclerView.LayoutParams) {
                return (RecyclerView.LayoutParams) layoutParams;
            }
        }
        return null;
    }

    /**
     * 获取 RecyclerView Item View LayoutParams
     *
     * @param recyclerView {@link RecyclerView}
     * @param position     索引
     *
     * @return Item View LayoutParams
     */
    public static RecyclerView.LayoutParams getLayoutParams(
        final RecyclerView recyclerView,
        final int position
    ) {
        return getLayoutParams(
            findViewByPosition(recyclerView, position)
        );
    }

    // =================
    // = LayoutManager =
    // =================

    /**
     * 设置 RecyclerView LayoutManager
     *
     * @param view          {@link View}
     * @param layoutManager LayoutManager
     *
     * @return {@link View}
     */
    public static View setLayoutManager(
        final View view,
        final RecyclerView.LayoutManager layoutManager
    ) {
        setLayoutManager(getRecyclerView(view), layoutManager);
        return view;
    }

    /**
     * 设置 RecyclerView LayoutManager
     *
     * @param recyclerView  {@link RecyclerView}
     * @param layoutManager LayoutManager
     * @param <T>           泛型
     *
     * @return {@link RecyclerView}
     */
    @SuppressWarnings("UnusedReturnValue")
    public static <T extends RecyclerView> T setLayoutManager(
        final T recyclerView,
        final RecyclerView.LayoutManager layoutManager
    ) {
        if (recyclerView != null && layoutManager != null) {
            recyclerView.setLayoutManager(layoutManager);
        }
        return recyclerView;
    }

    /**
     * 获取 RecyclerView LayoutManager
     *
     * @param view {@link View}
     *
     * @return LayoutManager
     */
    public static RecyclerView.LayoutManager getLayoutManager(final View view) {
        return getLayoutManager(getRecyclerView(view));
    }

    /**
     * 获取 RecyclerView LayoutManager
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return LayoutManager
     */
    public static RecyclerView.LayoutManager getLayoutManager(final RecyclerView recyclerView) {
        if (recyclerView != null) {
            return recyclerView.getLayoutManager();
        }
        return null;
    }

    // =

    /**
     * 获取 LinearLayoutManager
     *
     * @param view {@link View}
     *
     * @return {@link LinearLayoutManager}
     */
    public static LinearLayoutManager getLinearLayoutManager(final View view) {
        return getLinearLayoutManager(getRecyclerView(view));
    }

    /**
     * 获取 LinearLayoutManager
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return {@link LinearLayoutManager}
     */
    public static LinearLayoutManager getLinearLayoutManager(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager instanceof LinearLayoutManager) {
            return (LinearLayoutManager) layoutManager;
        }
        return null;
    }

    /**
     * 获取 GridLayoutManager
     *
     * @param view {@link View}
     *
     * @return {@link GridLayoutManager}
     */
    public static GridLayoutManager getGridLayoutManager(final View view) {
        return getGridLayoutManager(getRecyclerView(view));
    }

    /**
     * 获取 GridLayoutManager
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return {@link GridLayoutManager}
     */
    public static GridLayoutManager getGridLayoutManager(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager instanceof GridLayoutManager) {
            return (GridLayoutManager) layoutManager;
        }
        return null;
    }

    /**
     * 获取 StaggeredGridLayoutManager
     *
     * @param view {@link View}
     *
     * @return {@link StaggeredGridLayoutManager}
     */
    public static StaggeredGridLayoutManager getStaggeredGridLayoutManager(final View view) {
        return getStaggeredGridLayoutManager(getRecyclerView(view));
    }

    /**
     * 获取 StaggeredGridLayoutManager
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return {@link StaggeredGridLayoutManager}
     */
    public static StaggeredGridLayoutManager getStaggeredGridLayoutManager(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return (StaggeredGridLayoutManager) layoutManager;
        }
        return null;
    }

    // =

    /**
     * 获取 RecyclerView 对应 Item View 索引
     *
     * @param view     {@link View}
     * @param itemView Item View
     *
     * @return 对应 Item View 索引
     */
    public static int getPosition(
        final View view,
        final View itemView
    ) {
        return getPosition(getRecyclerView(view), itemView);
    }

    /**
     * 获取 RecyclerView 对应 Item View 索引
     *
     * @param recyclerView {@link RecyclerView}
     * @param itemView     Item View
     *
     * @return 对应 Item View 索引
     */
    public static int getPosition(
        final RecyclerView recyclerView,
        final View itemView
    ) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager != null && itemView != null) {
            try {
                return layoutManager.getPosition(itemView);
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return -1;
    }

    // =

    /**
     * 获取 RecyclerView 对应索引 Item View
     *
     * @param view     {@link View}
     * @param position 索引
     *
     * @return 对应索引 Item View
     */
    public static View findViewByPosition(
        final View view,
        final int position
    ) {
        return findViewByPosition(getRecyclerView(view), position);
    }

    /**
     * 获取 RecyclerView 对应索引 Item View
     *
     * @param recyclerView {@link RecyclerView}
     * @param position     索引
     *
     * @return 对应索引 Item View
     */
    public static View findViewByPosition(
        final RecyclerView recyclerView,
        final int position
    ) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager != null && position >= 0) {
            try {
                return layoutManager.findViewByPosition(position);
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return null;
    }

    // =

    /**
     * 获取 RecyclerView 第一条显示 Item 索引
     *
     * @param view {@link View}
     *
     * @return 第一条显示 Item 索引
     */
    public static int findFirstVisibleItemPosition(final View view) {
        return findFirstVisibleItemPosition(getRecyclerView(view));
    }

    /**
     * 获取 RecyclerView 第一条显示 Item 索引
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return 第一条显示 Item 索引
     */
    public static int findFirstVisibleItemPosition(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        return -1;
    }

    /**
     * 获取 RecyclerView 最后一条显示 Item 索引
     *
     * @param view {@link View}
     *
     * @return 最后一条显示 Item 索引
     */
    public static int findLastVisibleItemPosition(final View view) {
        return findLastVisibleItemPosition(getRecyclerView(view));
    }

    /**
     * 获取 RecyclerView 最后一条显示 Item 索引
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return 最后一条显示 Item 索引
     */
    public static int findLastVisibleItemPosition(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        return -1;
    }

    // =

    /**
     * 获取 RecyclerView 第一条显示 Item 索引数组
     *
     * @param view {@link View}
     *
     * @return 第一条显示 Item 索引数组
     */
    public static int[] findFirstVisibleItemPositions(final View view) {
        return findFirstVisibleItemPositions(getRecyclerView(view));
    }

    /**
     * 获取 RecyclerView 第一条显示 Item 索引数组
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return 第一条显示 Item 索引数组
     */
    public static int[] findFirstVisibleItemPositions(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null);
        }
        return null;
    }

    /**
     * 获取 RecyclerView 最后一条显示 Item 索引数组
     *
     * @param view {@link View}
     *
     * @return 最后一条显示 Item 索引数组
     */
    public static int[] findLastVisibleItemPositions(final View view) {
        return findLastVisibleItemPositions(getRecyclerView(view));
    }

    /**
     * 获取 RecyclerView 最后一条显示 Item 索引数组
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return 最后一条显示 Item 索引数组
     */
    public static int[] findLastVisibleItemPositions(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
        }
        return null;
    }

    // ===============
    // = Orientation =
    // ===============

    /**
     * 设置 RecyclerView Orientation
     *
     * @param view        {@link View}
     * @param orientation 方向
     *
     * @return {@link View}
     */
    public static View setOrientation(
        final View view,
        @RecyclerView.Orientation final int orientation
    ) {
        setOrientation(getRecyclerView(view), orientation);
        return view;
    }

    /**
     * 设置 RecyclerView Orientation
     *
     * @param recyclerView {@link RecyclerView}
     * @param orientation  方向
     * @param <T>          泛型
     *
     * @return {@link RecyclerView}
     */
    @SuppressWarnings("UnusedReturnValue")
    public static <T extends RecyclerView> T setOrientation(
        final T recyclerView,
        @RecyclerView.Orientation final int orientation
    ) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layoutManager).setOrientation(orientation);
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).setOrientation(orientation);
        }
        return recyclerView;
    }

    // =

    /**
     * 获取 RecyclerView Orientation
     *
     * @param view {@link View}
     *
     * @return Orientation
     */
    @RecyclerView.Orientation
    public static int getOrientation(final View view) {
        return getOrientation(getRecyclerView(view));
    }

    /**
     * 获取 RecyclerView Orientation
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return Orientation
     */
    @RecyclerView.Orientation
    public static int getOrientation(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).getOrientation();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getOrientation();
        }
        return RecyclerView.VERTICAL;
    }

    // =

    /**
     * 校验 RecyclerView Orientation 是否为 HORIZONTAL
     *
     * @param view {@link View}
     *
     * @return {@code true} yes, {@code false} no
     */
    public static boolean canScrollHorizontally(final View view) {
        return canScrollHorizontally(getRecyclerView(view));
    }

    /**
     * 校验 RecyclerView Orientation 是否为 HORIZONTAL
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return {@code true} yes, {@code false} no
     */
    public static boolean canScrollHorizontally(final RecyclerView recyclerView) {
        return getOrientation(recyclerView) == RecyclerView.HORIZONTAL;
    }

    /**
     * 校验 RecyclerView Orientation 是否为 VERTICAL
     *
     * @param view {@link View}
     *
     * @return {@code true} yes, {@code false} no
     */
    public static boolean canScrollVertically(final View view) {
        return canScrollVertically(getRecyclerView(view));
    }

    /**
     * 校验 RecyclerView Orientation 是否为 VERTICAL
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return {@code true} yes, {@code false} no
     */
    public static boolean canScrollVertically(final RecyclerView recyclerView) {
        return getOrientation(recyclerView) == RecyclerView.VERTICAL;
    }

    // ===========
    // = Adapter =
    // ===========

    /**
     * 设置 RecyclerView Adapter
     *
     * @param view    {@link View}
     * @param adapter Adapter
     *
     * @return {@link View}
     */
    public static View setAdapter(
        final View view,
        final RecyclerView.Adapter<?> adapter
    ) {
        setAdapter(getRecyclerView(view), adapter);
        return view;
    }

    /**
     * 设置 RecyclerView Adapter
     *
     * @param recyclerView {@link RecyclerView}
     * @param adapter      Adapter
     * @param <T>          泛型
     *
     * @return {@link RecyclerView}
     */
    @SuppressWarnings("UnusedReturnValue")
    public static <T extends RecyclerView> T setAdapter(
        final T recyclerView,
        final RecyclerView.Adapter<?> adapter
    ) {
        if (recyclerView != null && adapter != null) {
            recyclerView.setAdapter(adapter);
        }
        return recyclerView;
    }

    /**
     * 获取 RecyclerView Adapter
     *
     * @param view {@link View}
     * @param <T>  泛型
     *
     * @return LayoutManager
     */
    public static <T extends RecyclerView.Adapter<?>> T getAdapter(final View view) {
        return getAdapter(getRecyclerView(view));
    }

    /**
     * 获取 RecyclerView Adapter
     *
     * @param recyclerView {@link RecyclerView}
     * @param <T>          泛型
     *
     * @return LayoutManager
     */
    @SuppressWarnings("unchecked")
    public static <T extends RecyclerView.Adapter<?>> T getAdapter(final RecyclerView recyclerView) {
        if (recyclerView != null) {
            RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
            if (adapter != null) {
                try {
                    return (T) adapter;
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
            }
        }
        return null;
    }

    // =

    /**
     * RecyclerView notifyItemRemoved
     *
     * @param view     {@link View}
     * @param position 索引
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean notifyItemRemoved(
        final View view,
        final int position
    ) {
        return notifyItemRemoved(getRecyclerView(view), position);
    }

    /**
     * RecyclerView notifyItemRemoved
     *
     * @param recyclerView {@link RecyclerView}
     * @param position     索引
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean notifyItemRemoved(
        final RecyclerView recyclerView,
        final int position
    ) {
        if (recyclerView != null) {
            RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
            if (adapter != null) {
                try {
                    adapter.notifyItemRemoved(position);
                    return true;
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
            }
        }
        return false;
    }

    // =

    /**
     * RecyclerView notifyItemInserted
     *
     * @param view     {@link View}
     * @param position 索引
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean notifyItemInserted(
        final View view,
        final int position
    ) {
        return notifyItemInserted(getRecyclerView(view), position);
    }

    /**
     * RecyclerView notifyItemInserted
     *
     * @param recyclerView {@link RecyclerView}
     * @param position     索引
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean notifyItemInserted(
        final RecyclerView recyclerView,
        final int position
    ) {
        if (recyclerView != null) {
            RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
            if (adapter != null) {
                try {
                    adapter.notifyItemInserted(position);
                    return true;
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
            }
        }
        return false;
    }

    // =

    /**
     * RecyclerView notifyItemMoved
     *
     * @param view         {@link View}
     * @param fromPosition 当前索引
     * @param toPosition   更新后索引
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean notifyItemMoved(
        final View view,
        final int fromPosition,
        final int toPosition
    ) {
        return notifyItemMoved(getRecyclerView(view), fromPosition, toPosition);
    }

    /**
     * RecyclerView notifyItemMoved
     *
     * @param recyclerView {@link RecyclerView}
     * @param fromPosition 当前索引
     * @param toPosition   更新后索引
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean notifyItemMoved(
        final RecyclerView recyclerView,
        final int fromPosition,
        final int toPosition
    ) {
        if (recyclerView != null) {
            RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
            if (adapter != null) {
                try {
                    adapter.notifyItemMoved(fromPosition, toPosition);
                    return true;
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
            }
        }
        return false;
    }

    // =

    /**
     * RecyclerView notifyDataSetChanged
     *
     * @param view {@link View}
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean notifyDataSetChanged(final View view) {
        return notifyDataSetChanged(getRecyclerView(view));
    }

    /**
     * RecyclerView notifyDataSetChanged
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return {@code true} success, {@code false} fail
     */
    @SuppressLint("NotifyDataSetChanged")
    public static boolean notifyDataSetChanged(final RecyclerView recyclerView) {
        if (recyclerView != null) {
            RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
            if (adapter != null) {
                try {
                    adapter.notifyDataSetChanged();
                    return true;
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
            }
        }
        return false;
    }

    // ==============
    // = SnapHelper =
    // ==============

    /**
     * 设置 RecyclerView LinearSnapHelper
     *
     * @param view {@link View}
     *
     * @return {@link LinearSnapHelper}
     */
    public static LinearSnapHelper attachLinearSnapHelper(final View view) {
        return attachLinearSnapHelper(getRecyclerView(view));
    }

    /**
     * 设置 RecyclerView LinearSnapHelper
     * <pre>
     *     滑动多页居中显示, 类似 Gallery
     * </pre>
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return {@link LinearSnapHelper}
     */
    public static LinearSnapHelper attachLinearSnapHelper(final RecyclerView recyclerView) {
        if (recyclerView != null) {
            LinearSnapHelper helper = new LinearSnapHelper();
            helper.attachToRecyclerView(recyclerView);
            return helper;
        }
        return null;
    }

    // =

    /**
     * 设置 RecyclerView PagerSnapHelper
     *
     * @param view {@link View}
     *
     * @return {@link PagerSnapHelper}
     */
    public static PagerSnapHelper attachPagerSnapHelper(final View view) {
        return attachPagerSnapHelper(getRecyclerView(view));
    }

    /**
     * 设置 RecyclerView PagerSnapHelper
     * <pre>
     *     每次滑动一页居中显示, 类似 ViewPager
     * </pre>
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return {@link PagerSnapHelper}
     */
    public static PagerSnapHelper attachPagerSnapHelper(final RecyclerView recyclerView) {
        if (recyclerView != null) {
            PagerSnapHelper helper = new PagerSnapHelper();
            helper.attachToRecyclerView(recyclerView);
            return helper;
        }
        return null;
    }

    // ==================
    // = ItemDecoration =
    // ==================

    /**
     * 获取 RecyclerView ItemDecoration 总数
     *
     * @param view {@link View}
     *
     * @return RecyclerView ItemDecoration 总数
     */
    public static int getItemDecorationCount(final View view) {
        return getItemDecorationCount(getRecyclerView(view));
    }

    /**
     * 获取 RecyclerView ItemDecoration 总数
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return RecyclerView ItemDecoration 总数
     */
    public static int getItemDecorationCount(final RecyclerView recyclerView) {
        if (recyclerView != null) {
            return recyclerView.getItemDecorationCount();
        }
        return 0;
    }

    // =

    /**
     * 获取 RecyclerView ItemDecoration
     *
     * @param view  {@link View}
     * @param index RecyclerView ItemDecoration 索引
     *
     * @return RecyclerView ItemDecoration
     */
    public static RecyclerView.ItemDecoration getItemDecorationAt(
        final View view,
        final int index
    ) {
        return getItemDecorationAt(getRecyclerView(view), index);
    }

    /**
     * 获取 RecyclerView ItemDecoration
     *
     * @param recyclerView {@link RecyclerView}
     * @param index        RecyclerView ItemDecoration 索引
     *
     * @return RecyclerView ItemDecoration
     */
    public static RecyclerView.ItemDecoration getItemDecorationAt(
        final RecyclerView recyclerView,
        final int index
    ) {
        if (recyclerView != null) {
            try {
                return recyclerView.getItemDecorationAt(index);
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return null;
    }

    // =

    /**
     * 添加 RecyclerView ItemDecoration
     *
     * @param view  {@link View}
     * @param decor RecyclerView ItemDecoration
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean addItemDecoration(
        final View view,
        final RecyclerView.ItemDecoration decor
    ) {
        return addItemDecoration(getRecyclerView(view), decor);
    }

    /**
     * 添加 RecyclerView ItemDecoration
     *
     * @param recyclerView {@link RecyclerView}
     * @param decor        RecyclerView ItemDecoration
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean addItemDecoration(
        final RecyclerView recyclerView,
        final RecyclerView.ItemDecoration decor
    ) {
        if (recyclerView != null && decor != null) {
            try {
                recyclerView.addItemDecoration(decor);
                return true;
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return false;
    }

    /**
     * 添加 RecyclerView ItemDecoration
     *
     * @param view  {@link View}
     * @param decor RecyclerView ItemDecoration
     * @param index 添加索引
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean addItemDecoration(
        final View view,
        final RecyclerView.ItemDecoration decor,
        final int index
    ) {
        return addItemDecoration(getRecyclerView(view), decor, index);
    }

    /**
     * 添加 RecyclerView ItemDecoration
     *
     * @param recyclerView {@link RecyclerView}
     * @param decor        RecyclerView ItemDecoration
     * @param index        添加索引
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean addItemDecoration(
        final RecyclerView recyclerView,
        final RecyclerView.ItemDecoration decor,
        final int index
    ) {
        if (recyclerView != null && decor != null) {
            try {
                recyclerView.addItemDecoration(decor, index);
                return true;
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return false;
    }

    // =

    /**
     * 移除 RecyclerView ItemDecoration
     *
     * @param view  {@link View}
     * @param decor RecyclerView ItemDecoration
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean removeItemDecoration(
        final View view,
        final RecyclerView.ItemDecoration decor
    ) {
        return removeItemDecoration(getRecyclerView(view), decor);
    }

    /**
     * 移除 RecyclerView ItemDecoration
     *
     * @param recyclerView {@link RecyclerView}
     * @param decor        RecyclerView ItemDecoration
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean removeItemDecoration(
        final RecyclerView recyclerView,
        final RecyclerView.ItemDecoration decor
    ) {
        if (recyclerView != null && decor != null) {
            try {
                recyclerView.removeItemDecoration(decor);
                return true;
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return false;
    }

    // =

    /**
     * 移除 RecyclerView ItemDecoration
     *
     * @param view  {@link View}
     * @param index RecyclerView ItemDecoration 索引
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean removeItemDecorationAt(
        final View view,
        final int index
    ) {
        return removeItemDecorationAt(getRecyclerView(view), index);
    }

    /**
     * 移除 RecyclerView ItemDecoration
     *
     * @param recyclerView {@link RecyclerView}
     * @param index        RecyclerView ItemDecoration 索引
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean removeItemDecorationAt(
        final RecyclerView recyclerView,
        final int index
    ) {
        if (recyclerView != null) {
            try {
                recyclerView.removeItemDecorationAt(index);
                return true;
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return false;
    }

    // ====================
    // = OnScrollListener =
    // ====================

    /**
     * 设置 RecyclerView ScrollListener
     *
     * @param view     {@link View}
     * @param listener ScrollListener
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean setOnScrollListener(
        final View view,
        final RecyclerView.OnScrollListener listener
    ) {
        return setOnScrollListener(getRecyclerView(view), listener);
    }

    /**
     * 设置 RecyclerView ScrollListener
     *
     * @param recyclerView {@link RecyclerView}
     * @param listener     ScrollListener
     *
     * @return {@code true} success, {@code false} fail
     */
    @SuppressWarnings("deprecation")
    public static boolean setOnScrollListener(
        final RecyclerView recyclerView,
        final RecyclerView.OnScrollListener listener
    ) {
        if (recyclerView != null && listener != null) {
            try {
                recyclerView.setOnScrollListener(listener);
                return true;
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return false;
    }

    // =

    /**
     * 添加 RecyclerView ScrollListener
     *
     * @param view     {@link View}
     * @param listener ScrollListener
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean addOnScrollListener(
        final View view,
        final RecyclerView.OnScrollListener listener
    ) {
        return addOnScrollListener(getRecyclerView(view), listener);
    }

    /**
     * 添加 RecyclerView ScrollListener
     *
     * @param recyclerView {@link RecyclerView}
     * @param listener     ScrollListener
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean addOnScrollListener(
        final RecyclerView recyclerView,
        final RecyclerView.OnScrollListener listener
    ) {
        if (recyclerView != null && listener != null) {
            try {
                recyclerView.addOnScrollListener(listener);
                return true;
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return false;
    }

    // =

    /**
     * 移除 RecyclerView ScrollListener
     *
     * @param view     {@link View}
     * @param listener ScrollListener
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean removeOnScrollListener(
        final View view,
        final RecyclerView.OnScrollListener listener
    ) {
        return removeOnScrollListener(getRecyclerView(view), listener);
    }

    /**
     * 移除 RecyclerView ScrollListener
     *
     * @param recyclerView {@link RecyclerView}
     * @param listener     ScrollListener
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean removeOnScrollListener(
        final RecyclerView recyclerView,
        final RecyclerView.OnScrollListener listener
    ) {
        if (recyclerView != null && listener != null) {
            try {
                recyclerView.removeOnScrollListener(listener);
                return true;
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return false;
    }

    // =

    /**
     * 清空 RecyclerView ScrollListener
     *
     * @param view {@link View}
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean clearOnScrollListeners(final View view) {
        return clearOnScrollListeners(getRecyclerView(view));
    }

    /**
     * 清空 RecyclerView ScrollListener
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean clearOnScrollListeners(final RecyclerView recyclerView) {
        if (recyclerView != null) {
            try {
                recyclerView.clearOnScrollListeners();
                return true;
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return false;
    }

    // =

    /**
     * 获取 RecyclerView 滑动状态
     *
     * @param view {@link View}
     *
     * @return RecyclerView 滑动状态
     */
    public static int getScrollState(final View view) {
        return getScrollState(getRecyclerView(view));
    }

    /**
     * 获取 RecyclerView 滑动状态
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return RecyclerView 滑动状态
     */
    public static int getScrollState(final RecyclerView recyclerView) {
        if (recyclerView != null) {
            return recyclerView.getScrollState();
        }
        return RecyclerView.SCROLL_STATE_IDLE;
    }

    // =

    /**
     * 获取 RecyclerView 嵌套滚动开关
     *
     * @param view {@link View}
     *
     * @return RecyclerView 嵌套滚动开关
     */
    public static boolean isNestedScrollingEnabled(final View view) {
        return isNestedScrollingEnabled(getRecyclerView(view));
    }

    /**
     * 获取 RecyclerView 嵌套滚动开关
     *
     * @param recyclerView {@link RecyclerView}
     *
     * @return RecyclerView 嵌套滚动开关
     */
    public static boolean isNestedScrollingEnabled(final RecyclerView recyclerView) {
        if (recyclerView != null) {
            return recyclerView.isNestedScrollingEnabled();
        }
        return false;
    }

    // =

    /**
     * 设置 RecyclerView 嵌套滚动开关
     *
     * @param view    {@link View}
     * @param enabled 嵌套滚动开关
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean setNestedScrollingEnabled(
        final View view,
        final boolean enabled
    ) {
        return setNestedScrollingEnabled(getRecyclerView(view), enabled);
    }

    /**
     * 设置 RecyclerView 嵌套滚动开关
     *
     * @param recyclerView {@link RecyclerView}
     * @param enabled      嵌套滚动开关
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean setNestedScrollingEnabled(
        final RecyclerView recyclerView,
        final boolean enabled
    ) {
        if (recyclerView != null) {
            recyclerView.setNestedScrollingEnabled(enabled);
            return true;
        }
        return false;
    }
}