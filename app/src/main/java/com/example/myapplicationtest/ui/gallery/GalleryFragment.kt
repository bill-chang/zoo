package com.example.myapplicationtest.ui.gallery

import Data.AnimalResultItem
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
//    private val binding get() = _binding!!
    private val galleryViewModel by viewModels<GalleryViewModel>()
    private val galleryArgs by navArgs<GalleryFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            val controller =
                requireActivity().findNavController(R.id.nav_host_fragment_content_main)
            setContent {
                LibraryMainView(
                    viewModel = galleryViewModel,
//                    navController = controller,
                    galleryFragmentArgs = galleryArgs,
                ) {
                    val action = GalleryFragmentDirections.toSlide(
                        titleCh = it.aNameCh.orEmpty(),
                        aNameEn = it.aNameEn.orEmpty(),
                        aFeature = it.aFeature.orEmpty(),
                        aUpdate = it.aUpdate.orEmpty(),
                        aAlsoKnown = it.aAlsoKnown.orEmpty(),
                        aBehavior = it.aBehavior.orEmpty(),
                        aDistribution = it.aDistribution.orEmpty(),
                        imgUrl = it.aPic01Url.orEmpty(),
                    )
//                    想研究一下如何用code寫action, argument
//                    controller.navigate(R.id.nav_slideshow)
                    controller.navigate(action)

                }
            }
        }
    }
}

@Composable
fun LibraryMainView(
    context: Context = LocalContext.current,
    viewModel: GalleryViewModel,
//    navController: NavController,
    galleryFragmentArgs: GalleryFragmentArgs,
    onClickListener: (AnimalResultItem) -> Unit,
) {
    val libraryData by viewModel.zooAnimalDataList.collectAsStateWithLifecycle(emptyList())
    val imageLoader = viewModel.imageLoader

//    val imageLoader = context.imageLoader.newBuilder()
//        .logger(DebugLogger())
//        .memoryCache { MemoryCache.Builder(context).maxSizePercent(0.1).build() }
//        .build()

    Column {
        LibraryIntroduceView(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            context = context,
            args = galleryFragmentArgs,
            imageLoader = imageLoader,
        )
        HorizontalDivider(thickness = 10.dp, color = Color.LightGray)
        AnimalIntroduceColumn(
            libraryData = libraryData,
            context = context,
            imageLoader = imageLoader,
            onClickListener = onClickListener
        )
    }
}

@Composable
fun AnimalIntroduceColumn(
    libraryData: List<AnimalResultItem>,
    context: Context, imageLoader: ImageLoader,
    onClickListener: (AnimalResultItem) -> Unit,
) {
    Column {
        ViewTitle(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
        )
        AnimalInfoColumn(
            modifier = Modifier.fillMaxSize(),
            animalResultData = libraryData,
            context = context,
            imageLoader = imageLoader,
            onClickListener = onClickListener
        )
    }
//    感覺可以從home使用 compose的 NavHost()帶下來 再研究一下
//    NavHost()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimalInfoColumn(
    modifier: Modifier,
    animalResultData: List<AnimalResultItem>,
    context: Context,
    imageLoader: ImageLoader,
    onClickListener: (AnimalResultItem) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(animalResultData) { index, item ->
            AnimalItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(5.dp)
                    .combinedClickable(
                        onClick = { onClickListener(item) }
                    ),
                context = context,
                imgUrl = item.aPic01Url.orEmpty(),
                imageLoader = imageLoader,
                title = item.aNameCh.orEmpty(),
                content = item.aFeature.orEmpty()
            )
            if (index != animalResultData.lastIndex) HorizontalDivider(
                thickness = 2.dp,
                color = Color.LightGray
            )
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
                .fillMaxHeight()
                .background(Color.Blue),
            model = ImageRequest.Builder(context)
                .data(imgUrl)
                .setHeader("User-Agent", "Mozilla/5.0")
                .crossfade(true)
                .build(),
            imageLoader = imageLoader,
            contentDescription = "AnimalImage",
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
        MiddleTextView(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            alignment = Alignment.CenterStart,
            content = title,
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
    args: GalleryFragmentArgs,
    imageLoader: ImageLoader,
) {
    Column(modifier = modifier) {
        LibraryImage(
            context = context,
            args = args,
            imageLoader = imageLoader,
        )
        LibraryDetailInfo(args = args)
    }
}

@Composable
fun LibraryImage(
    context: Context,
    args: GalleryFragmentArgs,
    imageLoader: ImageLoader,
) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        model = ImageRequest.Builder(context)
            .data(args.imgUrl)
            .setHeader("User-Agent", "Mozilla/5.0")
            .crossfade(true)
            .build(),
        imageLoader = imageLoader,
        contentDescription = "LibraryImage",
        contentScale = ContentScale.Fit,
        placeholder = painterResource(R.drawable.ic_menu_gallery),
        error = painterResource(R.drawable.ic_launcher_foreground),
    )
}

@Composable
fun LibraryDetailInfo(
    modifier: Modifier = Modifier,
    args: GalleryFragmentArgs,
) {
    Column(modifier = modifier.padding(5.dp)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            text = args.libraryContent,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 20.sp)
        )
        BottomLibraryInfo(args = args)
    }
}

@Composable
fun BottomLibraryInfo(
    args: GalleryFragmentArgs
) {
    Column {
        TopInfoLine(args = args)
        BottomInfoLine(
            leftContent = args.category.ifBlank { "無" },
            rightUrl = args.eUrl.ifBlank { "無" }
        )
    }
}

@Composable
fun TopInfoLine(args: GalleryFragmentArgs) {
    MiddleTextView(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        content = args.memo.ifBlank { "無" },
        alignment = Alignment.CenterStart
    )
}

@Composable
fun BottomInfoLine(
    leftContent: String = "",
    rightUrl: String = "",
) {
    Row {
        Text(
            modifier = Modifier
                .size(240.dp, 40.dp),
            text = leftContent,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 20.sp)
        )
        Text(
            buildAnnotatedString {
                withLink(
                    LinkAnnotation.Url(
                        rightUrl,
                        TextLinkStyles(style = SpanStyle(color = Color.Blue))
                    )
                ) { append("在網頁中開啟") }
            },
            modifier = Modifier
                .fillMaxSize(),
            style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.End)
        )
    }
}

@Composable
fun MiddleTextView(
    modifier: Modifier = Modifier,
    content: String,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    alignment: Alignment,
) {
    Box(
        modifier = modifier,
        contentAlignment = alignment
    ) {
        Text(
            text = content,
            maxLines = 1,
            overflow = overflow,
            style = TextStyle(fontSize = 20.sp)
        )
    }
}