package com.woosung.compose.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woosung.compose.domain.model.Good
import com.woosung.compose.presentation.R
import com.woosung.compose.presentation.model.GoodUi
import com.woosung.compose.presentation.util.PriceUtil

@Composable
fun ProductItem(modifier : Modifier = Modifier, good: GoodUi) {
    Column(modifier) {
        Box() {
            NetworkImage(
                url = good.thumbnailURL,
                contentDescription = good.brandName,
                debugPlaceholder = R.drawable.test,
            )
            if (good.hasCoupon) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(
                            color = Color.DarkGray
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        text = "쿠폰",
                        color = Color.White,
                    )
                }
            }
        }
        Spacer(Modifier.height(8.dp))

        Text(
            text = good.brandName,
            modifier = Modifier,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
            ) {
            Text(
                text = stringResource(R.string.string_won, PriceUtil.addCommas(good.price)),
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(R.string.string_sale, good.saleRate),
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.Red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    ProductItem(
        good = GoodUi(
            linkURL = "123",
            thumbnailURL = "123",
            brandName = "널디",
            price = 20900,
            saleRate = 50,
            hasCoupon = true
        )
    )
}
