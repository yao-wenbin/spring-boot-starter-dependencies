package io.github.yaowenbin.common;

import cn.hutool.core.util.StrUtil;

import java.util.function.Supplier;

/**
 * @Author yaowenbin
 * @Date 2023/7/3
 */
public class Obj {


    /**
     * @param val 判空值
     * @param def 默认值
     * @return 当val不为空时，直接返回;否则，调用supplier获得默认值。 参考Map接口的getOrDefault API
     * @param <T> 任何参数
     */
    public static <T> T getOrDefault(T val, T def) {
        return val == null ? def : val;
    }

    /**
     * @param val 判空值
     * @param defaultSupplier 默认值提供者
     * @return 同上，但以Supplier函数式接口实现default值获取的懒加载，当default默认值的获取比较重操作的时候使用(比如从缓存中获取User)
     * @param <T> 任何参数
     */
    public static <T> T getOrDefault(T val, Supplier<T> defaultSupplier) {
        return val == null ? defaultSupplier.get() : val;
    }

    /**
     * 同{@link Obj#getOrDefault(Object, Object)} 但以StrUtil.isEmpty判空
     */
    public static String getOrDefault(String val, String def) {
        return StrUtil.isBlank(val) ? def : val;
    }

    /**
     * 同{@link Obj#getOrDefault(Object, Supplier)} 但以StrUtil.isEmpty判空
     */
    public static String getOrDefault(String val, Supplier<String> defaultSupplier) {
        return StrUtil.isBlank(val) ? defaultSupplier.get() : val;
    }

}
