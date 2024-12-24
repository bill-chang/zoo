package com.example.myapplicationtest.ui.slideshow

import Data.AnimalResultItem
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.memory.MemoryCache
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.example.myapplicationtest.R
import com.example.myapplicationtest.databinding.FragmentSlideshowBinding
import dagger.hilt.android.AndroidEntryPoint
import viewModel.SlideshowViewModel

@AndroidEntryPoint
class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val slideshowViewModel by viewModels<SlideshowViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val imageLoader = context.imageLoader.newBuilder()
                    .logger(DebugLogger())
                    .memoryCache { MemoryCache.Builder(context).maxSizePercent(0.1).build() }
                    .build()
                AnimalIntroduceDetail(
                    imgUrl = "",
                    imageLoader = imageLoader,
                    viewModel = slideshowViewModel,
                    navController = requireActivity().findNavController(this@SlideshowFragment.id)
                )
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


@Composable()
fun AnimalIntroduceDetail(
    context: Context = LocalContext.current,
    imgUrl: String,
    imageLoader: ImageLoader,
    viewModel: SlideshowViewModel,
    navController: NavController,
) {
    val libraryData by viewModel.animalDataList.collectAsStateWithLifecycle(AnimalResultItem())
    val libraryData1 by viewModel.animalDetailItem.collectAsStateWithLifecycle(AnimalResultItem())
    LaunchedEffect(libraryData) {
        if (libraryData != AnimalResultItem()){
            viewModel.getPassData(navController)
        }
    }

    Column {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Blue),
            model = ImageRequest.Builder(context)
                .data(imgUrl)
                .setHeader("User-Agent", "Mozilla/5.0")
                .crossfade(true)
                .build(),
            imageLoader = imageLoader,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            placeholder = painterResource(R.drawable.ic_menu_gallery),
            error = painterResource(R.drawable.ic_launcher_foreground),
        )
    AnimalDetailContent(libraryData1 = libraryData1,)
    }
}

@Composable
fun AnimalDetailContent(
    libraryData1: AnimalResultItem,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ){
        Text(
            modifier = Modifier.wrapContentSize(),
            text =  libraryData1.aNameCh.orEmpty(),
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 20.sp)
        )
        Text(
            modifier = Modifier.wrapContentSize(),
            text =  libraryData1.aNameEn.orEmpty(),
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 20.sp)
        )
        Spacer(Modifier.height(20.dp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "別名",
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 20.sp)
        )
        Text(
            modifier = Modifier.wrapContentSize(),
            text = libraryData1.aAlsoKnown.orEmpty(),
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 20.sp)
        )
        Spacer(Modifier.height(20.dp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "簡介",
            overflow = TextOverflow.Clip,
            style = TextStyle(fontSize = 20.sp)
        )
        Text(
            modifier = Modifier.wrapContentSize(),
            text = libraryData1.aDistribution.orEmpty(),
            overflow = TextOverflow.Clip,
            style = TextStyle(fontSize = 20.sp)
        )
        Spacer(Modifier.height(20.dp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "辨認方式",
            overflow = TextOverflow.Clip,
            style = TextStyle(fontSize = 20.sp)
        )
        Text(
            modifier = Modifier.wrapContentSize(),
            text = libraryData1.aFeature.orEmpty(),
            overflow = TextOverflow.Clip,
            style = TextStyle(fontSize = 20.sp)
        )
        Spacer(Modifier.height(20.dp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "功能性",
            overflow = TextOverflow.Clip,
            style = TextStyle(fontSize = 20.sp)
        )
        Text(
            modifier = Modifier.wrapContentSize(),
            text = libraryData1.aBehavior.orEmpty(),
            overflow = TextOverflow.Clip,
            style = TextStyle(fontSize = 20.sp)
        )
        Spacer(Modifier.height(20.dp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "最後更新${libraryData1.aUpdate?.ifBlank { "無" }}",
            overflow = TextOverflow.Clip,
            style = TextStyle(fontSize = 20.sp)
        )
    }
}