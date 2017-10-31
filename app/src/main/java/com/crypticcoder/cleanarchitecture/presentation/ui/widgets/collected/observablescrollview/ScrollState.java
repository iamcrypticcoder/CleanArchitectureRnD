package com.crypticcoder.cleanarchitecture.presentation.ui.widgets.collected.observablescrollview;

/**
 * Created by mahbub.kr on 2/19/2015.
 */
/**
 * Constants that indicates the scroll state of the Scrollable widgets.
 */
public enum ScrollState {
    /**
     * Widget is stopped.
     * This state does not always mean that this widget have never been scrolled.
     */
    STOP,

    /**
     * Widget is scrolled up by swiping it down.
     */
    UP,

    /**
     * Widget is scrolled down by swiping it up.
     */
    DOWN,
}