## Введение
Эта библиотека избавит вас от boilerplate-кода, если вы используете такие View как:
- LoadingView (любая view, которая отображает загрузку)
- NoInternetView (любая view, которая отображает отсутствие соединения с сетью)
- EmptyContentView (любая view, которая отображает отсутствие данных)
- ErrorView (любая view, которая отображает ошибки)
- ContentView (любая view, которая отображает данные)

Данная библиотека расчитана на тех, кто использует **Kotlin**, **RxJava|RxKotlin 2** и **ViewModel** из **Android Architecture Components**

Все логики отображения и скрытия ActionViews уже написаны за вас, вам остается только пользоваться библиотекой!

## Как пользоваться?
В библиотеке есть стандартные поведения для ActionViews. **ActionView** - любая View, которая реагирует на какое-то действие. В нашем случае это: 
- Loading - отображение загрузки
- NoInternet - отображение отсутствия соединения с сетью
- EmptyContent - отображение отсутствия данных, хотя они должны быть
- Error - отображение ошибки
- Content - отображение содержимого

**Базовое поведение** - логики скрытия и показа ActionView в строго заданные моменты. Подробнее с базовым поведением вы можете ознакомиться тут: [rus](https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5).

Чтобы использовать базовое поведение вам достаточно сделать несколько шагов:
- Унаследовать ваши Activity/Fragment от [ActionsActivity](https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/activity/ActionsActivity.kt)/[ActionsFragment](https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/fragment/ActionsFragment.kt):  
`abstract class BaseActivity : ActionsActivity()`  
`abstract class BaseFragment : ActionsFragment()`  
- Унаследовать вашу ViewModel от [ActionsViewModel](https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/viewmodel/ActionsViewModel.kt):  
`abstract class BaseViewModel constructor() : ActionsViewModel()`  
- Использовать предложенные реализации ActionViews **вставить ссылку на описание готовых ActionViews и как ими пользоваться** или создать свою **вставить ссылку на гайд по созданию своего CustomActionView с описанием того почему и зачем это надо**
- Добавить их в layout в котором они будут использоваться: **привести примеры кодом и вставить ссылку с описанием того, что для дефолтных ActionView**
- Если вам надо получить доступ к одной из ActionView внутри вашего Activity/Fragment, то вы можете сделать это через import с заменой имени: или без замены имени:
- Добавить `.withActionViews` в ваш rx-поток: 
```sh
dataRepository.getAll()
    .withActionViews(this)
    .execute({
        data.value = it
        isContentEmpty.value = gifts.value!!.isEmpty()
    })
```
Эти действия могут показаться сложными, но как показывает практика, для своего проекта вы сделаете это один раз, а дальше просто будете использовать данный механизм.

## FAQ
### Я использую kotlinx и у меня показывается **такая ошибка**, что делать?
Если вы используете реализации ActionViews, которые содержатся в библиотеке, то просто используйте ActionView отсюда: Если у вас две и более ActionView одинакового типа на экране, то используйте вспомогательный метод для инициализации View, который я сделал для вас: 
### Что делать, если у меня несколько ActionView одинакового типа на одном экране? 
Описать, что если у вас на экране больше одной ActionView одного и того же типа, то:
Если вы хотите использовать базовое поведение, то: вставить ссылку
Если вы хотите использовать кастомное поведение
### Что делать, если мне не подходит базовое поведение и я хочу использовать свое?
### Что делать, если мой Activity/Fragment/ViewModel наследуется от класса, который я не могу изменить?
Тогда вы можете просто скопировать код [ActionsActivity](https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/activity/ActionsActivity.kt) или [ActionsFragment](https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/fragment/ActionsFragment.kt) и создать необходимый класс руками
### Что делать, если я хочу добавить свой тип ActionView?
А оно вам надо? Разве данных типов ActionView недостаточно? Если нет, то посмотрите исходные коды на примере LoadingView, где она используется, как с ней работать и сделайте тоже самое.
### Как создать свою реализацию ActionView?
Показать примеры реализации
###
###


**Описать все типы интерфейсов ActionView, типа TopLoadingView, SwipeRefreshLayout в отдельном документе, описать когда какой использовать и зачем они нужны, как добавлять свой тип ActionView. 
Описать как работать с каждой из ActionView**, например, для EmptyContent нужно устанавливать isContentEmpty


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

