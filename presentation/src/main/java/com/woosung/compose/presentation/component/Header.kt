package com.woosung.compose.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.woosung.compose.presentation.R
import com.woosung.compose.presentation.model.HeaderUi
import com.woosung.compose.presentation.util.debugPlaceholder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Header(header: HeaderUi, actionClicked: (String) -> Unit = {}) {
    TopAppBar(
        title = {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Text(header.title, style = MaterialTheme.typography.titleSmall, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                    )
                if (header.iconURL != null) {
                    Spacer(Modifier.width(10.dp))
                    AsyncImage(
                        modifier = Modifier.size(24.dp),
                        placeholder = debugPlaceholder(R.drawable.ic_access_time_24),
                        model = header.iconURL,
                        contentDescription = ""
                    )
                }
            }
        },
        actions = {
            if (header.linkURL != null) {
                TextButton(onClick = { actionClicked(header.linkURL) }) {
                    Text(stringResource(R.string.string_all))
                }
            }
        }
    )
}


@Preview
@Composable
private fun MsHeaderPreview() {
    Header(
        HeaderUi(
            title = "Title",
            iconURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
            linkURL = "https://www.google.com"
        )
    )
}
