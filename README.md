# knife
Some widgets I often used in projects for Android

# Setup

* In your `build.gradle` :

```gradle
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.github.nanjingboy:knife:1.1.0'
}
```

# Usage

### Widgets

* [TitleSwitch](sample/src/main/res/layout/activity_title_switch_demo.xml)
* [TitleEditText](sample/src/main/res/layout/activity_title_edit_text_demo.xml)
* [TitleAndValueTextView](sample/src/main/res/layout/activity_title_and_value_text_view_demo.xml)
* [ClearEditText](sample/src/main/res/layout/activity_clear_edit_text_demo.xml)
* [SearchEditText](sample/src/main/res/layout/activity_search_edit_text_demo.xml)
* [SingleSelectDialog](sample/src/main/java/me/tom/knife/sample/MainActivity.java#L64-L70)
* [RightIconTitleAndValueTextView](sample/src/main/res/layout/activity_right_icon_title_and_value_text_view.xml)

### Activities

* [AbstractSingleSelectListActivity](sample/src/main/java/me/tom/knife/sample/UserSingleSelectListActivity.java)

### [Example](sample/src/main)

# License

MIT