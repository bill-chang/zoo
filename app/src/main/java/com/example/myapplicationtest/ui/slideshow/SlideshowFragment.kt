package com.example.myapplicationtest.ui.slideshow

import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.memory.MemoryCache
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.example.myapplicationtest.R
import com.example.myapplicationtest.databinding.FragmentSlideshowBinding
import com.example.myapplicationtest.ui.gallery.LibraryMainView
import dagger.hilt.android.AndroidEntryPoint
import viewModel.HomeViewModel
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


//        val slideshowViewModel: SlideshowViewModel by viewModels()

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val navController = requireActivity().findNavController(R.id.nav_host_fragment_content_main)
//        return root
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
                    viewModel = slideshowViewModel
                ){
//                    navController.navigate(Profile())
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        slideshowViewModel.getPassData(requireActivity().findNavController(this.id))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


@Composable()
fun AnimalIntroduceDetail(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    imgUrl: String,
    imageLoader: ImageLoader,
    viewModel: SlideshowViewModel,
    onClickListener: View.OnClickListener,
) {

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
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "" ,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 20.sp)
        )
    }

}