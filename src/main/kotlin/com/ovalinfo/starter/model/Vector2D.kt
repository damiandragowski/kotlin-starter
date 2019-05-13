package com.ovalinfo.starter.model

data class Vector2D(var x: Int = 0, var y: Int = 0) {
    operator fun plus(rhs: Vector2D): Vector2D = Vector2D(this.x+rhs.x, this.y+rhs.y)
    operator fun minus(rhs: Vector2D): Vector2D = Vector2D(this.x-rhs.x, this.y-rhs.y)
    operator fun times(rhs: Vector2D): Int = this.x*rhs.x + this.y*rhs.y
    operator fun unaryMinus():Vector2D = Vector2D(-this.x, -this.y)
}