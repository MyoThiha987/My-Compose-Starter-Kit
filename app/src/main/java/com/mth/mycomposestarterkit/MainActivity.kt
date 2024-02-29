package com.mth.mycomposestarterkit

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.mth.mycomposestarterkit.ui.theme.MyComposeStarterKitTheme
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeStarterKitTheme {
                val service = PostDataSource.provideClient(this)

                val scope = rememberCoroutineScope()
                val data = remember {
                    mutableStateOf(listOf<Post>())
                }
                val hazeState = remember {
                    HazeState()
                }
                val state = rememberLazyListState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val navController = rememberNavController()
                    MainScreen(navController = navController)
                    //ProfileScreen()
                    //HomeDestination()
                   // DetailScreen()
                    //SelectSeatScreen()
                    //PaymentScreen()
                    //TicketListScreen()
                    //MyTicketDetailScreen()
                    /*AnimatedBorder(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(16.dp),
                        gradientColor = Brush.sweepGradient(
                            listOf(
                                Color.Yellow, Color.Cyan,
                                Color.Magenta
                            )
                        )

                    ) {
                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(
                                    brush = Brush.linearGradient(
                                        listOf(
                                            Color.Yellow,
                                            Color.Cyan
                                        )
                                    )
                                )
                                .size(height = 150.dp, width = 200.dp)
                                .shineEffect()
                        )
                        *//*Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(16.dp)
                                .shineEffect(),
                            text = "jfkdfjkdjfkdjfkdjfkdjfkdjfkdjfkjdkjfkdjfkdjfkdjfkjdfkjdfkjd"
                        )*//*
                    }*/

                    /*scope.launch {
                        val aa = service.getPosts()
                        service.createPost(post = Post(body = "I Love Youuuuuuu...", title = "Heyyy My Crush.....", userId = 1))
                        data.value = aa
                    }
                    App(data.value)*/

                    /*LazyColumn(
                        modifier = Modifier.haze(
                            state = hazeState,
                            backgroundColor = MaterialTheme.colorScheme.background,
                            tint = Color.Black.copy(0.2f),
                            blurRadius = 10.dp, noiseFactor = 30f
                        ),
                        state = state
                    ) {
                        items(items = data.value) { post ->
                            CreditCardSample(
                                title = post.title.orEmpty(), body = post.body.orEmpty()
                            )
                        }
                    }*/


                }
            }
        }
    }
}


@Composable
fun CreditCardSample(
    modifier: Modifier = Modifier, title: String, body: String
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.DarkGray, RoundedCornerShape(12.dp))
            .border(
                width = Dp.Hairline,
                color = Color.Black.copy(alpha = .5f),
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))


        //color = MaterialTheme.colorScheme.surface
    ) {
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp)) {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = body,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyComposeStarterKitTheme {
        HomeScreen()
        //CreditCardSample(title = "AA", body = "BBB")
    }
}

@Serializable
data class Post(
    val body: String? = null, val title: String? = null, val userId: Int? = 0
)

interface PostDataSource {
    suspend fun getPosts(): List<Post>
    suspend fun createPost(post: Post): Post?

    companion object {
        fun provideClient(context: Context): PostDataSource {
            val converter = KotlinxSerializationConverter(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })

            // Create the Interceptor
            val chuckerInterceptor =
                ChuckerInterceptor.Builder(context).collector(ChuckerCollector(context))
                    .maxContentLength(250000L).build()

            val okhttpEngine = OkHttp.create {
                addInterceptor(chuckerInterceptor)
            }

            return PostDataSourceImpl(client = HttpClient(okhttpEngine) {
                expectSuccess = true

                install(ContentNegotiation) {
                    register(
                        ContentType.Application.Json, converter
                    )
                }

                install(Logging) {
                    level = LogLevel.ALL
                }


                install(ResponseObserver) {
                    onResponse { response ->
                        Log.d("HTTP status:", "${response.status.value}")
                    }
                }

                install(DefaultRequest) {
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                    header("Authorization", "Bearer edjjfjjffjfj")
                }

                defaultRequest {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }


            })
        }
    }
}

class PostDataSourceImpl(
    private val client: HttpClient
) : PostDataSource {
    override suspend fun getPosts(): List<Post> {
        return try {
            client.get { url(HttpRoute.POSTS) }.body()
        } catch (e: RedirectResponseException) {
            Log.d("RE", "${e.response.status.description}")
            listOf()

        } catch (e: ClientRequestException) {
            Log.d("RE", "${e.response.status.description}")
            listOf()

        } catch (e: ServerResponseException) {
            Log.d("RE", "${e.response.status.description}")
            listOf()
        }
    }

    override suspend fun createPost(post: Post): Post {
        return try {
            client.post(HttpRoute.POSTS) {
                contentType(ContentType.Application.Json)
                setBody(post)
            }.body()
        } catch (e: RedirectResponseException) {
            Log.d("RE", "${e.response.status.description}")
            Post()


        } catch (e: ClientRequestException) {
            Log.d("RE", "${e.response.status.description}")
            Post()
        } catch (e: ServerResponseException) {
            Log.d("RE", "${e.response.status.description}")
            Post()
        }
    }

    object HttpRoute {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
        const val POSTS = "$BASE_URL/posts"
    }
}

@Composable
fun App(data: List<Post>) {

    val hazeState = remember { HazeState() }
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(64.dp)
                    .hazeChild(
                        state = hazeState,
                        tint = Color.Black.copy(0.2f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = Dp.Hairline,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = .8f),
                                Color.Black.copy(alpha = .2f),
                            ),
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .noRippleClickable {
                        Toast
                            .makeText(context, "Click", Toast.LENGTH_SHORT)
                            .show()
                    }
            ) {
                Text(
                    text = "AAA",
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.Center),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { padding ->

        LazyColumn(
            Modifier
                .haze(
                    hazeState,
                    backgroundColor = MaterialTheme.colorScheme.background,
                    tint = Color.Black.copy(alpha = .2f),
                    blurRadius = 10.dp,
                )
                .fillMaxSize()
                .padding(12.dp),

            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {


            items(items = data) {
                CreditCardSample(title = it.title.orEmpty(), body = it.body.orEmpty())

                /*Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.DarkGray, RoundedCornerShape(12.dp))
                        .border(
                            width = Dp.Hairline,
                            color = Color.White.copy(alpha = .5f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    *//*AsyncImage(
                        model = "https://source.unsplash.com/random?neon,$it",
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )*//*
                }*/
            }
        }
    }
}

inline fun Modifier.noRippleClickable(
    crossinline onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@Composable
fun GlassmorphicBottomNavigation(hazeState: HazeState) {
    var selectedTabIndex by remember { mutableIntStateOf(1) }
    Box(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 64.dp)
            .fillMaxWidth()
            .height(64.dp)
            .hazeChild(state = hazeState, tint = Color.Black.copy(0.2f), shape = CircleShape)
            .border(
                width = Dp.Hairline,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = .8f),
                        Color.White.copy(alpha = .2f),
                    ),
                ),
                shape = CircleShape
            )
    ) {
        BottomBarTabs(
            tabs,
            selectedTab = selectedTabIndex,
            onTabSelected = {
                selectedTabIndex = tabs.indexOf(it)
            }
        )

        val animatedSelectedTabIndex by animateFloatAsState(
            targetValue = selectedTabIndex.toFloat(), label = "animatedSelectedTabIndex",
            animationSpec = spring(
                stiffness = Spring.StiffnessLow,
                dampingRatio = Spring.DampingRatioLowBouncy,
            )
        )

        val animatedColor by animateColorAsState(
            targetValue = tabs[selectedTabIndex].color,
            label = "animatedColor",
            animationSpec = spring(
                stiffness = Spring.StiffnessLow,
            )
        )

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
        ) {
            val tabWidth = size.width / tabs.size
            drawCircle(
                color = animatedColor.copy(alpha = .6f),
                radius = size.height / 2,
                center = Offset(
                    (tabWidth * animatedSelectedTabIndex) + tabWidth / 2,
                    size.height / 2
                )
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        ) {
            val path = Path().apply {
                addRoundRect(RoundRect(size.toRect(), CornerRadius(size.height)))
            }
            val length = PathMeasure().apply { setPath(path, false) }.length

            val tabWidth = size.width / tabs.size
            drawPath(
                path,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        animatedColor.copy(alpha = 0f),
                        animatedColor.copy(alpha = 1f),
                        animatedColor.copy(alpha = 1f),
                        animatedColor.copy(alpha = 0f),
                    ),
                    startX = tabWidth * animatedSelectedTabIndex,
                    endX = tabWidth * (animatedSelectedTabIndex + 1),
                ),
                style = Stroke(
                    width = 6f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(length / 2, length)
                    )
                )
            )
        }
    }
}

@Composable
fun BottomBarTabs(
    tabs: List<BottomBarTab>,
    selectedTab: Int,
    onTabSelected: (BottomBarTab) -> Unit,
) {
    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        ),
        LocalContentColor provides Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            for (tab in tabs) {
                val alpha by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .35f,
                    label = "alpha"
                )
                val scale by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .98f,
                    visibilityThreshold = .000001f,
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                    ),
                    label = "scale"
                )
                Column(
                    modifier = Modifier
                        .scale(scale)
                        .alpha(alpha)
                        .fillMaxHeight()
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                onTabSelected(tab)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(imageVector = tab.icon, contentDescription = "tab ${tab.title}")
                    Text(text = tab.title)
                }
            }
        }
    }
}

sealed class BottomBarTab(val title: String, val icon: ImageVector, val color: Color) {
    data object Profile : BottomBarTab(
        title = "Profile",
        icon = Icons.Rounded.Person,
        color = Color(0xFFFFA574)
    )

    data object Home : BottomBarTab(
        title = "Home",
        icon = Icons.Rounded.Home,
        color = Color(0xFFFA6FFF)
    )

    data object Settings : BottomBarTab(
        title = "Settings",
        icon = Icons.Rounded.Settings,
        color = Color(0xFFADFF64)
    )
}

val tabs = listOf(
    BottomBarTab.Profile,
    BottomBarTab.Home,
    BottomBarTab.Settings,
)