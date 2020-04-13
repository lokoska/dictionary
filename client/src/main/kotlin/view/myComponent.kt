package view

import react.RProps
import react.dom.h1
import lib.stateLessRComponent

class MyProps(val prop1: String) : RProps

val myComponent = stateLessRComponent<MyProps> {
    h1 {
        +"myComponent, ${it.prop1}"
    }
}
