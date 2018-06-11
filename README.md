## Описание
Если ваше приложение:
* Работает с сетью, БД
* Отображает данные
* Отображает индикаторы загрузки
* Отображает отсутствие соединения с сетью
* Отображает отсутствие данных
* Отображает какие-либо другие ошибки

То данная библиотека избавит вас от boilerplate-кода и автоматизирует показ и скрытие View для отображения:
* Содержимого, данных - [ContentView]  
* Загрузки - [LoadingView]  
* Отсутствия соединения с сетью - [NoInternetView]  
* Отсутствия данных - [EmptyContentView]  
* Ошибок - [ErrorView]  

Данная библиотека расчитана на тех, кто использует **Kotlin**, **RxJava 2|RxKotlin 2** и **ViewModel** из **Android Architecture Components**

Все логики отображения и скрытия ActionViews уже написаны за вас, вам остается только пользоваться библиотекой!

## Термины
**Базовое поведение** или **Стандартное поведение** - логики скрытия и показа ActionView в строго заданные моменты.

**ActionView** - любая View, которая реагирует на какое-то действие. В библиотеке описаны несколько типов ActionView и их базовое поведение.  

Подробнее с базовым поведением и типами ActionViews вы можете ознакомиться [тут](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews).

## Как пользоваться?
Чтобы начать использовать библиотеку вам достаточно сделать несколько шагов:
* Добавить dependency в gradle: ```implementation `ссылка на зависимость в репозитории` ```
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
    * через import с заменой имени, если вы используете kotlin-android-extensions:  
`import kotlinx.android.synthetic.main.fr_gifts.contentView as recyclerView`  
    * через переменные(они проинициализированы в ActionsActivity/ActionsFragment): `contentActionView`, `loadingActionView`, `noInternetActionView`, `emptyContentActionView`, `errorActionView`  
При использовании данного способа, сейчас не представляется возможности изменить название переменных. Если у вас есть предложения, как это сделать, буду рад выслушать.
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
* Вы можете отказаться от наследования [ActionsActivity]/[ActionsFragment], но все равно вы будете обязаны наследовать вашу ViewModel от ActionsViewModel
* В вашей Activity/Fragment вы должны проинициализировать ваши ActionViews и подписаться на обновления тех ActionView, которые вам нужны, также как это сделано в [ActionsActivity]/[ActionsFragment]

**В чем плюсы от использования такого способа?**  
* Вы избавляетесь от строго заданных id для ActionViews и можете использовать абсолютно любые

**В чем минусы от использования такого способа?**
* Вы должны инициализировать ваши ActionViews сами, но это можно сделать с помощью kotlin-android-extensions или ButterKnife или, также как это сделано в [ActionsActivity]/[ActionsFragment]
* Вы должны подписываться на обновления руками, также как это сделано в [ActionsActivity]/[ActionsFragment]

## Более глубокое использование библиотеки
* Базовое поведение и типы ActionViews
* Использование готовых реализаций ActionViews
* Кастомное поведение ActionViews
* Использование нескольких ActionView одинакового типа на одном экране
* Создание собственной реализации ActionView
* Удобный способ добавления NoInternetView/EmptyContentView/ProgressBar в layout.
* Вспомогательные методы

## Что ещё умеет библиотека?
В библиотеке есть несколько полезных extension-методов, которые упростят разработку. Найти их вы можете [здесь][SupportMethods].

## FAQ
### Мне надоело каждый раз добавлять одну и ту же реализацию NoInternetView/EmptyContentView/... в layout. Что делать?
Показать удобный способ с помощью `include layout`
### Я использую kotlinx и у меня показывается **такая ошибка**, что делать?
Если вы используете реализации ActionViews, которые содержатся в библиотеке, то просто используйте ActionView отсюда: Если у вас две и более ActionView одинакового типа на экране, то используйте вспомогательный метод для инициализации View, который я сделал для вас: 
### Что делать, если у меня несколько ActionView одинакового типа на одном экране? 
Описать, что если у вас на экране больше одной ActionView одного и того же типа, то:
Показать с базовым поведением, оставить ссылку на кастомное поведение
### Что делать, если мне не подходит базовое поведение и я хочу использовать свое?
### Что делать, если я не могу наследоваться от ActionsActivity/ActionsFragment/ActionsViewModel? т.к. моя Activity/Fragment/ViewModel наследуется от базового класса, который я не могу изменить?
Тогда вы можете просто скопировать код [ActionsActivity] или [ActionsFragment] и создать необходимый класс руками
### Что делать, если я хочу добавить свой тип ActionView?
А оно вам надо? Разве данных типов ActionView недостаточно? Если нет, то посмотрите исходные коды на примере LoadingView, где она используется, как с ней работать и сделайте тоже самое.
###

Чтобы использовать базовое поведение вам достаточно сделать несколько простых шагов:
* создать кастомные view или использовать те, которые я уже создал для вас - **показать, что некоторые кастомные view я уже сделал за них. показать какие есть. показать как создавать кастомные view - в отдельный документ**
* добавить их в layout, в котором они будут использоваться:
* проинициализировать ActionView в коде - **обратить внимание на баг с kotlinx, показать, что дефолтные actionView инициализировать не надо, их надо лишь получить и если надо, то заменить имя в импорте**
* и добавить .withActionViews в ваш rx поток: **показать пример кодом**
* **показать, какие вспомогательные классы и методы есть, описать их - в отдельный документ**
* **показать как использовать и добавлять кастомные логики - в отдельный документ**
* **показать, что надо наследоваться от ActionViews-классов, Fragment, Activity, ViewModel - в начало документа**

# Как это работает?


[ActionsActivity]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/activity/ActionsActivity.kt>
[ActionsFragment]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/fragment/ActionsFragment.kt>
[ActionsViewModel]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/viewmodel/ActionsViewModel.kt>
[LoadingView]: <https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#loadingview>
[NoInternetView]: <https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#nointernetview>
[EmptyContentView]: <https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#emptycontentview>
[ErrorView]: <https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#errorview>
[ContentView]: <https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#contentview>
