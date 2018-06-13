[ ![Download](https://api.bintray.com/packages/tanchuev/Maven/actionviews-viewmodel/images/download.svg) ](https://bintray.com/tanchuev/Maven/actionviews-viewmodel/_latestVersion) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Описание
Если ваше приложение:
* Работает с сетью, БД
* Отображает данные
* Отображает индикаторы загрузки
* Отображает отсутствие соединения с сетью
* Отображает отсутствие данных
* Отображает какие-либо другие ошибки
* Использует [Kotlin](https://kotlinlang.org/)
* Использует [RxJava 2](https://github.com/ReactiveX/RxJava) или [RxKotlin 2](https://github.com/ReactiveX/RxKotlin)
* Использует [ViewModel из Android Architecture Components](https://developer.android.com/topic/libraries/architecture/viewmodel)

То данная библиотека избавит вас от boilerplate-кода и автоматизирует показ и скрытие View для отображения:
* Содержимого, данных - [ContentView]  
* Загрузки - [LoadingView]  
* Отсутствия соединения с сетью - [NoInternetView]  
* Отсутствия данных - [EmptyContentView]  
* Ошибок - [ErrorView]  

Если ваше приложение использует MVP - то есть ActionViews-MVP - в разработке  
Если ваше приложение использует kotlin-couroutines и MVVM - то есть (ActionViews-ViewModel-Coroutines)[ссылка на будущую библиотеку, если я придумаю как внедрить этот механизм в корутины]  
Если ваше приложение использует kotlin-couroutines и MVP - то есть (ActionViews-MVP-Coroutines)[ссылка на будущую библиотеку, если я придумаю как внедрить этот механизм в корутины]  

Все логики отображения и скрытия ActionViews уже написаны за вас, вам остается только пользоваться библиотекой!

## Термины
**Базовое поведение** или **Стандартное поведение** - логики скрытия и показа ActionView в строго заданные моменты.

**ActionView** - любая View, которая реагирует на какое-то событие/действие. В библиотеке описано несколько типов ActionView и их базовое поведение.  

Подробнее с базовым поведением и типами ActionViews вы можете ознакомиться [тут](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews).

## Как пользоваться?
Чтобы начать использовать библиотеку вам достаточно сделать несколько шагов:
* Добавить dependency в gradle: ```implementation 'com.github.tanchuev:actionviews-viewmodel:0.7.0'```
* Унаследовать ваши Activity/Fragment от [ActionsActivity]/[ActionsFragment]:  
`abstract class BaseActivity : ActionsActivity()`  
`abstract class BaseFragment : ActionsFragment()`  
* Унаследовать вашу ViewModel от [ActionsViewModel]:  
`abstract class BaseViewModel constructor() : ActionsViewModel()`  
* [Использовать готовые реализации ActionViews](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%98%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D0%B3%D0%BE%D1%82%D0%BE%D0%B2%D1%8B%D1%85-%D1%80%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B9-ActionViews) или [создать свою реализацию ActionView](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%A1%D0%BE%D0%B7%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-CustomActionView)
* Добавить ActionViews в layout:
```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.tanchuev.actionviews.viewmodel.widget.NoInternetView
        android:id="@+id/noInternetView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="center"
        android:background="@color/white"
        android:gravity="center"
        android:visibility="gone"
        app:buttonText="@string/tryAgain"
        app:icon="@drawable/ic_no_internet"
        app:text="@string/errorNoInternet" />

    <com.tanchuev.actionviews.viewmodel.widget.EmptyContentView
        android:id="@+id/emptyContentView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:gravity="center"
        android:visibility="gone"
        app:buttonText="@string/tryAgain"
        app:icon="@drawable/ic_empty_content"
        app:text="@string/errorEmptyContent" />

    <com.tanchuev.actionviews.viewmodel.widget.ProgressBar
        android:id="@+id/loadingView"
        style="?android:attr/progressBarStyleHorizontal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:indeterminate="true"
        android:visibility="gone"
        app:progressColor="@color/black"
        tools:visibility="visible" />

    <ScrollView
        android:id="@+id/contentView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <!-- some views for show data -->
    </ScrollView>

    <!-- also you can add some views outside ContentView -->
</FrameLayout>
```
* **Не забыть указать им строго заданные id**:
  * [ContentView] - `contentView`
  * [LoadingView] - `loadingView`
  * [NoInternetView] - `noInternetView`
  * [EmptyContentView] - `emptyContentView`  
Примеры можете посмотреть [тут](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%98%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D0%B3%D0%BE%D1%82%D0%BE%D0%B2%D1%8B%D1%85-%D1%80%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B9-ActionViews).
* Если вам надо получить доступ к одной из ActionView внутри вашего Activity/Fragment, то вы можете сделать это
    * через import с заменой имени, если вы используете [kotlin-android-extensions]:  
`import kotlinx.android.synthetic.main.fr_gifts.contentView as recyclerView`  
    * через переменные(они проинициализированы в [ActionsActivity]/[ActionsFragment]): `contentActionView`, `loadingActionView`, `noInternetActionView`, `emptyContentActionView`, `errorActionView`  
При использовании данного способа, сейчас нет возможности изменить название переменных. Если у вас есть предложения, как это сделать - буду рад выслушать.
* Добавить `.withActionViews(viewModel: ActionsViewModel)` в ваш rx-поток: 
```kotlin
dataRepository.getAll()
    .withActionViews(this)
    .execute({
        data.value = it
        isContentEmpty.value = gifts.value!!.isEmpty()
    })
```
Эти действия могут показаться сложными, но как показывает практика, для своего проекта вы сделаете это один раз, а дальше просто будете использовать данный механизм.

## Другой вариант использования ActionViews
* Вы можете отказаться от наследования [ActionsActivity]/[ActionsFragment], но все равно вы будете обязаны наследовать вашу ViewModel от [ActionsViewModel]
* В вашей Activity/Fragment вы должны проинициализировать ваши ActionViews и подписаться на обновления тех ActionView, которые вам нужны, также как это сделано в [ActionsActivity]/[ActionsFragment]

**В чем плюсы от использования такого способа?**  
* Вы избавляетесь от строго заданных id для ActionViews и можете использовать абсолютно любые

**В чем минусы от использования такого способа?**
* Вы должны инициализировать ваши ActionViews сами, но это можно сделать с помощью [kotlin-android-extensions](https://kotlinlang.org/docs/tutorials/android-plugin.html) или [ButterKnife](http://jakewharton.github.io/butterknife/) или, также как это сделано в [ActionsActivity]/[ActionsFragment]
* Вы должны подписываться на обновления руками, также как это сделано в [ActionsActivity]/[ActionsFragment]

## Более глубокое использование библиотеки
* [Базовое поведение и типы ActionViews](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews)
* [Использование готовых реализаций ActionViews](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%98%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D0%B3%D0%BE%D1%82%D0%BE%D0%B2%D1%8B%D1%85-%D1%80%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B9-ActionViews)
* [Создание собственной реализации ActionView](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%A1%D0%BE%D0%B7%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-CustomActionView)
* [Кастомное поведение ActionViews](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%9A%D0%B0%D1%81%D1%82%D0%BE%D0%BC%D0%BD%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5)
* [Использование нескольких ActionView одинакового типа на одном экране](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%98%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D0%BD%D0%B5%D1%81%D0%BA%D0%BE%D0%BB%D1%8C%D0%BA%D0%B8%D1%85-ActionView-%D0%BE%D0%B4%D0%B8%D0%BD%D0%B0%D0%BA%D0%BE%D0%B2%D0%BE%D0%B3%D0%BE-%D1%82%D0%B8%D0%BF%D0%B0-%D0%BD%D0%B0-%D0%BE%D0%B4%D0%BD%D0%BE%D0%BC-%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B5)
* [Удобный способ добавления ActionView в layout](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%A3%D0%B4%D0%BE%D0%B1%D0%BD%D1%8B%D0%B9-%D1%81%D0%BF%D0%BE%D1%81%D0%BE%D0%B1-%D0%B4%D0%BE%D0%B1%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F-ActionView-%D0%B2-layout)
* [Вспомогательные методы](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%92%D1%81%D0%BF%D0%BE%D0%BC%D0%BE%D0%B3%D0%B0%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5-%D0%BC%D0%B5%D1%82%D0%BE%D0%B4%D1%8B)

## FAQ
### Мне надоело каждый раз добавлять одну и ту же реализацию NoInternetView/EmptyContentView/... в layout. Что делать?
[Удобный способ добавления ActionView в layout](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%A3%D0%B4%D0%BE%D0%B1%D0%BD%D1%8B%D0%B9-%D1%81%D0%BF%D0%BE%D1%81%D0%BE%D0%B1-%D0%B4%D0%BE%D0%B1%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F-ActionView-%D0%B2-layout)
### Я использую [kotlin-android-extensions] и у меня показывает ошибку импорта view, что делать?
Если вы используете реализации ActionViews, которые содержатся в библиотеке, то просто используйте ActionView отсюда: Если у вас две и более ActionView одинакового типа на экране, то используйте вспомогательный метод для инициализации View, который я сделал для вас: 
### Что делать, если у меня несколько ActionView одинакового типа на одном экране? 
[Использование нескольких ActionView одинакового типа на одном экране](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%98%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D0%BD%D0%B5%D1%81%D0%BA%D0%BE%D0%BB%D1%8C%D0%BA%D0%B8%D1%85-ActionView-%D0%BE%D0%B4%D0%B8%D0%BD%D0%B0%D0%BA%D0%BE%D0%B2%D0%BE%D0%B3%D0%BE-%D1%82%D0%B8%D0%BF%D0%B0-%D0%BD%D0%B0-%D0%BE%D0%B4%D0%BD%D0%BE%D0%BC-%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B5)
### Что делать, если мне не подходит базовое поведение и я хочу использовать свое?
[Кастомное поведение](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%9A%D0%B0%D1%81%D1%82%D0%BE%D0%BC%D0%BD%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5)
### Моя Activity/Fragment/ViewModel наследуется от базового класса, который я не могу изменить. Как добавить наследование от ActionsActivity/ActionsFragment/ActionsViewModel?
Тогда вы можете просто скопировать код [ActionsActivity] или [ActionsFragment] или [ActionsFragment] и создать необходимый класс руками
### Что делать, если я хочу добавить свой тип ActionView?
А оно вам надо? Разве данных типов ActionView недостаточно? Если нет, то посмотрите исходные коды на примере [LoadingView], где она используется, как с ней работать и сделайте тоже самое.

## Лицензии
```
MIT License

Copyright (c) 2018 Marat Tanchuev

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

[ActionsActivity]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/activity/ActionsActivity.kt>
[ActionsFragment]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/fragment/ActionsFragment.kt>
[ActionsViewModel]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/viewmodel/ActionsViewModel.kt>
[LoadingView]: <https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#loadingview>
[NoInternetView]: <https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#nointernetview>
[EmptyContentView]: <https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#emptycontentview>
[ErrorView]: <https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#errorview>
[ContentView]: <https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#contentview>
[kotlin-android-extensions]: <https://kotlinlang.org/docs/tutorials/android-plugin.html>
