package es.estech.myapplication.ui.adapters

import es.estech.myapplication.data.models.breeds.Breed

interface OnHolderClick {
    fun click (raza: Breed, position: Int)
}