package com.rex.project.helep.model

data class RouteModels (
    val features: List<Feature>
)

data class Feature (
    val geometry: Geometry
)

data class Geometry (
    val coordinates: List<List<Double>>
)