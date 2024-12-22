package com.example.myapplicationtest.ui.gallery

import Data.AnimalResultData
import Data.AnimalResultItem
import Data.ZooResultItem
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.memory.MemoryCache
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.example.myapplicationtest.R
import com.example.myapplicationtest.databinding.FragmentGalleryBinding
import com.example.myapplicationtest.ui.home.ItemEndView
import dagger.hilt.android.AndroidEntryPoint
import viewModel.GalleryViewModel

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
        // 要處理第一個動物園葉面帶過來的資料
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
    val imageLoader = context.imageLoader.newBuilder()
        .logger(DebugLogger())
        .memoryCache { MemoryCache.Builder(context).maxSizePercent(0.1).build() }
        .build()

    Column {
        val libraryData by viewModel.zooLibraryData1.collectAsStateWithLifecycle(emptyList())
        LibraryIntroduceView(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            context = context,
            mainLibraryData = ZooResultItem(),
            imageLoader = imageLoader,
        )
        HorizontalDivider(thickness = 10.dp, color = Color.Gray)
        AnimalIntroduceColumn(modifier = Modifier.padding(5.dp), libraryData = libraryData, context = context, imageLoader = imageLoader)
    }
}

@Composable
fun AnimalIntroduceColumn(modifier: Modifier = Modifier, libraryData: List<AnimalResultItem>,context: Context,imageLoader: ImageLoader) {
    Column {
        ViewTitle(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
        )
        AnimalInfoColumn(modifier = Modifier.fillMaxSize(), animalResultData = libraryData, context = context, imageLoader = imageLoader)
    }
}

@Composable
fun AnimalInfoColumn(
    modifier: Modifier,
    animalResultData: List<AnimalResultItem>,
    context: Context,
    imageLoader: ImageLoader
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(animalResultData) { index, item ->
            AnimalItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(5.dp),
                context = context,
                imgUrl =  item.aPic01Url.orEmpty(),
                imageLoader = imageLoader,
                title = item.aNameCh.orEmpty(),
                content = item.aFeature.orEmpty()
            )
            if (index!=animalResultData.lastIndex) HorizontalDivider(thickness = 2.dp, color = Color.Gray)
        }
    }
}

@Composable
fun AnimalItem(
    modifier: Modifier = Modifier,
    context: Context,
    imgUrl: String,
    imageLoader: ImageLoader,
    title: String,
    content: String,
) {
    Row(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .width(100.dp)
                .fillMaxHeight(),
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
        AnimalContent(
            modifier = Modifier
                .width(250.dp)
                .fillMaxHeight(),
            title = title,
            content = content
        )
        ItemEndView(modifier = Modifier.fillMaxSize(), imageSize = 20.dp)
    }
}

@Composable
fun AnimalContent(modifier: Modifier = Modifier, title: String, content: String) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 20.sp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            text = content,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 20.sp)
        )
    }
}

@Composable
fun ViewTitle(modifier: Modifier = Modifier) {
    Text(modifier = modifier, text = "植物資料", style = TextStyle(fontSize = 24.sp))
}

@Composable
fun LibraryIntroduceView(
    modifier: Modifier = Modifier,
    context: Context,
    mainLibraryData: ZooResultItem,
    imageLoader: ImageLoader,
) {
    Column(modifier = modifier) {
        LibraryImage(
            context = context,
            mainLibraryData = mainLibraryData,
            imageLoader = imageLoader
        )
        LibraryDetailInfo(mainLibraryData = mainLibraryData)
    }
}

@Composable
fun LibraryImage(
    modifier: Modifier = Modifier,
    context: Context,
    mainLibraryData: ZooResultItem,
    imageLoader: ImageLoader
) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        model = ImageRequest.Builder(context)
            .data("${mainLibraryData.ePicUrl}")
            .size(270, 270)
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
fun LibraryDetailInfo(
    modifier: Modifier = Modifier,
    mainLibraryData: ZooResultItem,
) {
    Column(modifier = modifier.padding(5.dp)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
//        text = item.eName.orEmpty(),
            text = "123",
//        maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 20.sp)
        )
        BottomLibraryInfo(modifier = Modifier.fillMaxSize())
    }


}

@Composable
fun BottomLibraryInfo(modifier: Modifier = Modifier) {
    Column {
        TopInfoLine()
        BottomInfoLine() {

        }
    }
}

@Composable
fun TopInfoLine() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        text = "123",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = TextStyle(fontSize = 20.sp)
    )

}

@Composable
fun BottomInfoLine(
    modifier: Modifier = Modifier,
    onClickListener: OnClickListener
) {
    Row {
        Text(
            modifier = Modifier
                .size(240.dp, 40.dp),
            text = "123",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 20.sp)
        )
        Text(
            modifier = Modifier
                .fillMaxSize(),
            text = "在網頁開啟",
            style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.End)
        )
    }
}