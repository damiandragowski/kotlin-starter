package com.ovalinfo.starter.model

import kotlin.math.sqrt

data class Vector2D(var x: Int = 0, var y: Int = 0) {
    operator fun plus(rhs: Vector2D): Vector2D = Vector2D(this.x+rhs.x, this.y+rhs.y)
    operator fun minus(rhs: Vector2D): Vector2D = Vector2D(this.x-rhs.x, this.y-rhs.y)
    operator fun times(rhs: Vector2D): Int = this.x*rhs.x + this.y*rhs.y
    operator fun unaryMinus():Vector2D = Vector2D(-this.x, -this.y)
    fun length() = sqrt((this.x*this.x+this.y*this.y).toDouble())
    fun perpendicular(): Vector2D = Vector2D(-this.y, this.x)
    override fun toString():String = "x="+this.x+",y="+this.y
    operator fun get(i:Int):Int = (if (i==0) this.x else this.y)
    operator fun invoke(v1 : Vector2D) = this.x*v1.y-this.y*v1.x
}