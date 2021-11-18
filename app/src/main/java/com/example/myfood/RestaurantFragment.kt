package com.example.myfood

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.atway.ui.adapter.ATAdapter
import com.example.myfood.databinding.FragmentRestBinding

class RestaurantFragment : Fragment(R.layout.fragment_rest) {

    private var binding: FragmentRestBinding? = null

    private val categoryAdapter = ATAdapter(
        { CategoryView(it)
        }
    )
    private val bannerAdapter = ATAdapter(
        { BannerView(it)
        }
    )

    private val shopAdapter = ATAdapter(
            { ShopView(it)
            }
    )


    private var filters = arrayOf(
            FilterItem(1, "Ordenar", closeIcon = R.drawable.ic_baseline_keyboard_arrow_down_24),
            FilterItem(2,"Pra retirar", icon = R.drawable.ic_baseline_directions_walk_24),
            FilterItem(3, "Entrega grátis", ),
            FilterItem(4,"Vale refeição", closeIcon = R.drawable.ic_baseline_keyboard_arrow_down_24),
            FilterItem(5,"Distância", closeIcon = R.drawable.ic_baseline_keyboard_arrow_down_24),
            FilterItem(6,"Entrega Parceria"),
            FilterItem(7,"Super Restaurante"),
            FilterItem(8,"Filtros", closeIcon = R.drawable.ic_baseline_filter_list_24)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter.items = arrayListOf(
            Category(1, "https://www.ifood.com.br/static/images/categories/market.png", "Mercado",
                0xFFB6D048),
            Category(2, "https://www.ifood.com.br/static/images/categories/restaurant.png", "Fast-Food",
                0xFFE91D2D
            ),
            Category(3, "https://www.ifood.com.br/static/images/categories/drinks.png", "Bebidas", 0xFFF6D553),
            Category(4, "https://static-images.ifood.com.br/image/upload/f_auto/webapp/landingV2/express.png", "Express", 0xFFFF0000),
            Category(5, "https://parceiros.ifood.com.br/static/media/salad.9db040c0.png", "Saudável", 0xFFE92D2D),
        )

        bannerAdapter.items = arrayListOf(
            Banner(1, "https://static-images.ifood.com.br/image/upload/t_high/discoveries/itensBasicosNOV21Principal_zE1X.png"),
            Banner(2, "https://static-images.ifood.com.br/image/upload/t_high/discoveries/Bebidas40offPrincipal_cljA.png"),
            Banner(3, "https://static-images.ifood.com.br/image/upload/t_high/discoveries/MerceariaeMatinaisPrincipal_mfDO.png")
        )

        shopAdapter.items = arrayListOf(
                Shop(1, "https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/201910292243_94aaf166-84cc-4ebf-a35d-d223be34d01f.png", "Coco Bambu"),
                Shop(2, "https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/201906182008_2b157a73-7564-4733-94c1-8d0376e7bb39.png", "Outback"),
                Shop(3, "https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/Logo%20McDonald_MCDON_DRIV15.jpg", "McDonald's"),
                Shop(4, "https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/201801231937__HABIB_VERDE.jpg", "Habib's"),
                Shop(5, "https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/201808031827_1670c674-e866-47ab-9d1a-2c9e3ed16eee.jpg", "Alibaba Árabe"),
                Shop(6, "https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde//pastelaria-a_PASTE_OPENA.jpg", "Pastelaria Afonso Pena"),
                Shop(7, "https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/62ebe1d1-3815-43d4-bf2b-c59172403935/202003261237_VxsJ_.jpeg", "Center Lanches")
        )



        binding = FragmentRestBinding.bind(view)

        binding?.let{
            it.rvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvCategory.adapter = categoryAdapter

            it.rvBanners.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvBanners.adapter = bannerAdapter

            it.rvShops.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvShops.adapter = shopAdapter

            it.rvBanners.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE){
                        notifyPositionChanged(recyclerView)
                    }
                }
            })





            filters.forEach { filter ->
                it.chipGroupFilter.addView(filter.toChip(requireContext()))
            }
        }

    }

    private fun addDots(container: LinearLayout, size: Int, position: Int){
        container.removeAllViews()

        Array(size){
            val textView = TextView(context).apply {
                text = getString(R.string.dotted)
                textSize = 20f
                setTextColor(
                        if (position == it) ContextCompat.getColor(context, android.R.color.black)
                else ContextCompat.getColor(context, android.R.color.darker_gray)
                )
            }
            container.addView(textView)
        }

    }

    private var position: Int? = RecyclerView.NO_POSITION
    private val snapHelper = LinearSnapHelper()

    private fun notifyPositionChanged(recyclerView: RecyclerView){

        val layoutManager = recyclerView.layoutManager
        val view = snapHelper.findSnapView(layoutManager)
        val position = if (view == null) RecyclerView.NO_POSITION else layoutManager?.getPosition(view)

        val positionChanged = this.position != position
        if (positionChanged){
            addDots(binding!!.dots, bannerAdapter.items.size, position ?: 0)
        }
        this.position = position

    }
}

