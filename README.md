## Введение
Эта библиотека избавит вас от boilerplate-кода, если вы используете такие View как:
- LoadingView (любая view, которая отображает загрузку)
- NoInternetView (любая view, которая отображает отсутствие соединения с сетью)
- EmptyContentView (любая view, которая отображает отсутствие данных)
- ErrorView (любая view, которая отображает ошибки)
- ContentView (любая view, которая отображает данные)

Данная библиотека расчитана на тех, кто использует **Kotlin**, **RxJava 2|RxKotlin 2** и **ViewModel** из **Android Architecture Components**

Все логики отображения и скрытия ActionViews уже написаны за вас, вам остается только пользоваться библиотекой!

## Как пользоваться?
В библиотеке есть стандартные поведения для ActionViews. **ActionView** - любая View, которая реагирует на какое-то действие. В библиотеке описаны 5 базовых типов ActionView: 
- [LoadingView]- отображение загрузки
- [NoInternetView] - отображение отсутствия соединения с сетью
- [EmptyContentView] - отображение отсутствия данных, хотя они должны быть
- [ErrorView] - отображение ошибки
- ContentView - отображение содержимого

**Базовое поведение** - логики скрытия и показа ActionView в строго заданные моменты. Подробнее с базовым поведением вы можете ознакомиться [тут](BasicLogics).

Чтобы начать использовать библиотеку вам достаточно сделать несколько шагов:
- Добавить dependency в gradle: ```implementation `ссылка на зависимость в репозитории` ```
- Унаследовать ваши Activity/Fragment от [ActionsActivity]/[ActionsFragment]:  
`abstract class BaseActivity : ActionsActivity()`  
`abstract class BaseFragment : ActionsFragment()`  
- Унаследовать вашу ViewModel от [ActionsViewModel]:  
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

## Другой вариант использования ActionViews
- Вы можете отказаться от наследования [ActionsActivity]/[ActionsFragment], но все равно вы будете обязаны наследовать вашу ViewModel от ActionsViewModel
- В вашей Activity/Fragment вы должны проинициализировать ваши ActionViews и подписаться на обновления тех ActionView, которые вам нужны, также как это сделано в [ActionsActivity]/[ActionsFragment]

**В чем плюсы от использования такого способа?**  
- Вы избавляетесь от строго заданных ID для ActionViews и можете использовать абсолютно любые

**В чем минусы от использования такого способа?**
- Вы должны инициализировать ваши ActionViews сами
- Вы должны подписываться на обновления руками, также как это сделано в [ActionsActivity]/[ActionsFragment]

## FAQ
### Я использую kotlinx и у меня показывается **такая ошибка**, что делать?
Если вы используете реализации ActionViews, которые содержатся в библиотеке, то просто используйте ActionView отсюда: Если у вас две и более ActionView одинакового типа на экране, то используйте вспомогательный метод для инициализации View, который я сделал для вас: 
### Что делать, если у меня несколько ActionView одинакового типа на одном экране? 
Описать, что если у вас на экране больше одной ActionView одного и того же типа, то:
Если вы хотите использовать базовое поведение, то: вставить ссылку
Если вы хотите использовать кастомное поведение
### Что делать, если мне не подходит базовое поведение и я хочу использовать свое?
### Что делать, если я не могу наследоваться от ActionsActivity/ActionsFragment/ActionsViewModel? т.к. моя Activity/Fragment/ViewModel наследуется от базового класса, который я не могу изменить?
Тогда вы можете просто скопировать код [ActionsActivity] или [ActionsFragment] и создать необходимый класс руками
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

[BasicLogics]: <https://github.com/tanchuev/ActionViews-ViewModel/wiki/%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5>
[ActionsActivity]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/activity/ActionsActivity.kt>
[ActionsFragment]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/fragment/ActionsFragment.kt>
[ActionsViewModel]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/viewmodel/ActionsViewModel.kt>
[LoadingView]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/view/LoadingView.kt>
[NoInternetView]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/view/NoInternetView.kt>
[EmptyContentView]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/view/EmptyContentView.kt>
[ErrorView]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/view/ErrorView.kt>
