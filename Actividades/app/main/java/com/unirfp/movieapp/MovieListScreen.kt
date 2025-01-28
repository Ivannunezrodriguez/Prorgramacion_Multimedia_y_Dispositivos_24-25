import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unirfp.movieapp.Movie

@Composable
fun MovieListScreen(movies: List<Movie>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}
