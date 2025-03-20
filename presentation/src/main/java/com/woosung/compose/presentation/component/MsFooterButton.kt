package com.woosung.compose.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.woosung.compose.domain.model.Footer
import com.woosung.compose.domain.model.FooterType
import com.woosung.compose.presentation.R
import com.woosung.compose.presentation.model.FooterUi
import com.woosung.compose.presentation.util.debugPlaceholder

@Composable
fun MsFooterButton(
    footer: FooterUi,
    onClicked: (FooterType) -> Unit = {}
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RoundedCornerShape(100))
            .clipToBounds(),
        shape = RoundedCornerShape(100),
        onClick = { onClicked(footer.type) },
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = Color.White,
        )
    ) {
        if (footer.iconURL != null) {
            AsyncImage(
                placeholder = debugPlaceholder(R.drawable.ic_access_time_24),
                model = footer.iconURL,
                contentDescription = ""
            )
            Spacer(modifier = Modifier)
        }
        Text(footer.title, color = Color.Black)
    }
}


@Preview(showBackground = true)
@Composable
private fun MsButtonPreview() {
    MsFooterButton(
        footer = FooterUi(
            title = "더보기",
            iconURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
            type = FooterType.REFRESH
        )
    )
}
