package com.lmptech.intune.domain.models

enum class DrawerState {
    CLOSED,
    OPENED;

}

fun DrawerState.isOpened() = this == DrawerState.OPENED

fun DrawerState.opposite(): DrawerState {
    return if (this == DrawerState.OPENED) DrawerState.CLOSED
    else DrawerState.OPENED
}

