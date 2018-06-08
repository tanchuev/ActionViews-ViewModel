## Введение
Эта библиотека избавит вас от boilerplate-кода, если вы используете такие View как:
- LoadingView (любая view, которая отображает загрузку)
- NoInternetView (любая view, которая отображает отсутствие соединения с сетью)
- EmptyContentView (любая view, которая отображает отсутствие данных)
- ErrorView (любая view, которая отображает ошибки)
- ContentView (любая view, которая отображает данные)

Данная библиотека расчитана на тех, кто использует **Kotlin**, **RxJava|RxKotlin 2** и **ViewModel** из **Android Architecture Components**

Все логики отображения ActionViews уже написаны за вас, вам остается только пользоваться библиотекой! Но если базовых логик вам будет недостаточно - вы можете легко дописать их сами под ваши нужды.

## Как пользоваться?
В библиотеке есть стандартные поведения для ActionViews. ActionView - любая View, которая реагирует на какое-то действие. В нашем случае это: 
- Loading - отображение загрузки
- NoInternet - отображение отсутствия соединения с сетью
- EmptyContent - отображение отсутствия данных, хотя они должны быть
- Error - отображение ошибки
- Content - отображение содержимого

**Описать все типы интерфейсов ActionView, типа TopLoadingView, SwipeRefreshLayout в отдельном документе, описать когда какой использовать и зачем они нужны, как добавлять свой тип ActionView. 
Описать как работать с каждой из ActionView**, например, для EmptyContent нужно устанавливать isContentEmpty

### Базовое поведение ActionViews - **вынести в отдельный документ, указать ссылку на ActionViewsExtensions**
#### LoadingView
***Отображается когда:*** Rx-поток начал свою работу  
***Скрывается когда:*** Rx-поток завершил свою работу  
***Когда LoadingView отображается:***  
```sh 
when (this) {
    is SwipeRefreshLayout -> {
        setVisibility(View.VISIBLE, this)
        view.post {
            this.isRefreshing = true
        }
    }
    is TopLoadingView -> {
        setVisibility(View.GONE, contentView)
        setVisibility(View.VISIBLE, view)
    }
    else -> {
        setVisibility(View.VISIBLE, view)
    }
}
```
***Когда LoadingView скрывается:***
```sh 
when (this) {
    null -> throw NullPointerException("LoadingView is null")
    is SwipeRefreshLayout -> this.post {
        this.isRefreshing = false
    }
    is TopLoadingView -> {
        setVisibility(View.VISIBLE, contentView)
        setVisibility(View.GONE, view)
    }
    else -> {
        setVisibility(View.GONE, view)
    }
}
```
##### NoInternetView
##### EmptyContentView
##### ErrorView
##### ContentView


Чтобы использовать базовое поведение вам достаточно сделать несколько простых шагов:
- создать кастомные view или использовать те, которые я уже создал для вас - **показать, что некоторые кастомные view я уже сделал за них. показать какие есть. показать как создавать кастомные view - в отдельный документ**
- добавить их в layout, в котором они будут использоваться:
- и не забыть указать им строгие id - **перечислить какие id для какой view надо использовать**
- проинициализировать ActionView в коде - **обратить внимание на баг с kotlinx, показать, что дефолтные actionView инициализировать не надо, их надо лишь получить и если надо, то заменить имя в импорте**
- и добавить .withActionViews в ваш rx поток: **показать пример кодом**
- **показать, какие вспомогательные классы и методы есть, описать их - в отдельный документ**
- **показать как использовать и добавлять кастомные логики - в отдельный документ**
- **показать, что надо наследоваться от ActionViews-классов, Fragment, Activity, ViewModel - в начало документа**

# Как это работает?

