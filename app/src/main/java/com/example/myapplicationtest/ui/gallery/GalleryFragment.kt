package com.example.myapplicationtest.ui.gallery

import Data.AnimalResultItem
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.imageLoader
import coil.memory.MemoryCache
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.example.myapplicationtest.R
import com.example.myapplicationtest.databinding.FragmentGalleryBinding
import com.example.myapplicationtest.ui.home.ZooLibraryHomeView
import dagger.hilt.android.AndroidEntryPoint
import viewModel.GalleryViewModel
import viewModel.HomeViewModel

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val galleryViewModel by viewModels<GalleryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
//        return root
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                LibraryMainView(viewModel = galleryViewModel)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@Composable
fun LibraryMainView(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: GalleryViewModel,
) {
    val libraryData by viewModel.zooLibraryData1.collectAsStateWithLifecycle(emptyList())
    Column {
        LibraryInfo(context = context, libraryData = libraryData)
        AnimalsInfo()
    }
}

@Composable
fun LibraryInfo(modifier: Modifier = Modifier, context: Context, libraryData: List<AnimalResultItem>) {
    val imageLoader = context.imageLoader.newBuilder()
        .logger(DebugLogger())
        .memoryCache { MemoryCache.Builder(context).maxSizePercent(0.1).build() }
        .build()

    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        model = ImageRequest.Builder(context)
            .data("")
            .size(270,270)
            .setHeader("User-Agent", "Mozilla/5.0")
            .crossfade(true)
            .build(),
        imageLoader = imageLoader,
        contentDescription = "",
        contentScale = ContentScale.Fit,
        placeholder = painterResource(R.drawable.ic_menu_gallery),
        error = painterResource(R.drawable.ic_launcher_foreground),
        )
}

@Composable
fun AnimalsInfo(modifier: Modifier = Modifier, libraryData: List<AnimalResultItem> = emptyList()) {

}