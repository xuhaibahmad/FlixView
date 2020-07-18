# FlixView

[ ![Download](https://api.bintray.com/packages/xuhaibahmad/maven/FlixView/images/download.svg) ](https://bintray.com/xuhaibahmad/maven/FlixView/_latestVersion)
[![](https://jitpack.io/v/xuhaibahmad/flixview.svg)](https://jitpack.io/#xuhaibahmad/flixview)

![Banner](screenshot.png)

## Table of Contents

- [About](#about)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## About

The Leanback library is quite limited when it comes to building Netflix-style catalog, specially the fixed pointer navigation for Android TV.
`FlixView` attempts to overcome the complexity by mixing `RecyclerView` with Leanback's `VerticalGridView` to build similar experience.

IMPORTANT: This library is meant for ONLY Android TV with D-pad navigation.

## Usage

1. Add JCenter repository in the `build.gradle` file in the root of your project:

```
repositories {
    google()
    jcenter()
}
```

2. Next, add the FlixView dependency in the `build.gradle` file of your app module:

```
implementation 'com.zuhaibahmad.flixview:flixview:<current-version>'
```

3. Add `FlixView` to your layout:

```
<com.zuhaibahmad.flixview.FlixView
    android:id="@+id/vFlixView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:banner_aspect_ratio="36:10"
    app:selection_foreground="@drawable/selection_foreground"
    app:content_width="@dimen/test_card_width"
    app:content_height="@dimen/test_card_height"
    app:content_title_background_color="@color/redPrimary"
    app:content_title_text_color="@color/textColorPrimary"
    app:content_title_text_size="@dimen/title_text_size"
    app:category_title_text_color="@color/textColorPrimary" />
```

4. Add data items to the view:

```
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    // Each category has multiple content items
    val contentItems = ListOf(
        Content(...),
        Content(...),
        Content(...),
    )
    val items = (1..5).map {
        Category(
            id = it.toString(),
            name = "Category # $it",
            items = contentItems.shuffled()
        )
    }
    vFlixView.apply {
        setItems(items)
        setOnChildClickedListener { _, thumbnail ->
            displayMessage("Clicked: ${thumbnail.label}")
        }
        setOnChildSelectedListener { _, thumbnail ->
            displayMessage("Selected: ${thumbnail.label}")
        }
    }
}
```

## Contributing

PRs and Issues are always welcomed.

## License

FlixView is [Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0.txt) licensed.
