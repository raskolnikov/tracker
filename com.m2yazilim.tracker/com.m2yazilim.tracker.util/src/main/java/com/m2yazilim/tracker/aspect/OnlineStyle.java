package com.m2yazilim.tracker.aspect;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringStyle;

public class OnlineStyle extends ToStringStyle {

    private static final long          serialVersionUID = 1L;

    private static final int           MAX_DEPTH        = 4;

    private final static ToStringStyle instance         = new OnlineStyle(MAX_DEPTH);
    /**
     * Setting {@link #maxDepth} to 0 will have the same effect as using original {@link #ToStringStyle}: it will print all 1st level values without
     * traversing into them. Setting to 1 will traverse up to 2nd level and so on.
     */
    private final int                  maxDepth;

    private int                        depth;

    public static ToStringStyle getInstance() {
        return instance;
    }

    public OnlineStyle() {
        this(MAX_DEPTH);
    }

    public OnlineStyle(final int maxDepth) {
        this.setArrayContentDetail(true);
        this.setUseShortClassName(true);
        this.setUseClassName(true);
        this.setUseIdentityHashCode(false);
        // this.setFieldSeparator(", " + SystemUtils.LINE_SEPARATOR + "  ");
        this.setUseFieldNames(true);
        this.maxDepth = maxDepth;
    }

    @Override
    protected void appendDetail(final StringBuffer buffer, final String fieldName, final Object value) {
        if (value.getClass().getName().startsWith("java.lang.") || ((this.maxDepth != MAX_DEPTH) && (this.depth >= this.maxDepth))) {
            buffer.append(value);
        } else {
            this.depth++;
            buffer.append(new OnlineReflectionToStringBuilder(value, this).toString());
            this.depth--;
        }
    }

    @Override
    protected void appendDetail(final StringBuffer buffer, final String fieldName, final Collection<?> coll) {
        this.depth++;
        buffer.append(new OnlineReflectionToStringBuilder(coll.toArray(), this).toString());
        this.depth--;
    }

    @Override
    protected void appendDetail(final StringBuffer buffer, final String fieldName, final Object[] array) {
        this.depth++;
        buffer.append(new OnlineReflectionToStringBuilder(array, this).toString());
        this.depth--;
    }

    @Override
    protected void appendDetail(final StringBuffer buffer, final String fieldName, final Map<?, ?> map) {
        this.depth++;
        buffer.append(new OnlineReflectionToStringBuilder(map, this).toString());
        this.depth--;
    }
}
