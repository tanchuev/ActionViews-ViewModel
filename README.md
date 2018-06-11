## Описание
Эта библиотека избавит вас от boilerplate-кода, если ваше приложение:
* Работает с сетью, БД
* Отображает данные
* Отображает индикаторы загрузки
* Отображает отсутствие соединения с сетью
* Отображает отсутствие данных
* Отображает какие-либо другие ошибки

Данная библиотека избавляет вас от boilerplate-кода и автоматизирует показ и скрытие View для отображения:
* Содержимого - [ContentView]  
* Загрузки - [LoadingView]  
* Отсутствия соединения с сетью - [NoInternetView]  
* Отсутствия данных - [EmptyContentView]  
* Ошибок - [ErrorView]  

Данная библиотека расчитана на тех, кто использует **Kotlin**, **RxJava 2|RxKotlin 2** и **ViewModel** из **Android Architecture Components**

Все логики отображения и скрытия ActionViews уже написаны за вас, вам остается только пользоваться библиотекой!

## Термины
**Базовое поведение** или **Стандартное поведение** - логики скрытия и показа ActionView в строго заданные моменты.  
**ActionView** - любая View, которая реагирует на какое-то действие. В библиотеке описаны несколько базовых типов ActionView и их базовое поведение.  
Подробнее с базовым поведением и типами ActionViews вы можете ознакомиться [тут](%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews).

## Как пользоваться?
Чтобы начать использовать библиотеку вам достаточно сделать несколько шагов:
* Добавить dependency в gradle: ```implementation `ссылка на зависимость в репозитории` ```
* Унаследовать ваши Activity/Fragment от [ActionsActivity]/[ActionsFragment]:  
`abstract class BaseActivity : ActionsActivity()`  
`abstract class BaseFragment : ActionsFragment()`  
* Унаследовать вашу ViewModel от [ActionsViewModel]:  
`abstract class BaseViewModel constructor() : ActionsViewModel()`  
* [Использовать предложенные реализации ActionViews][CompletedActionViews] или [создать свою реализацию ActionView][CreateCustomActionView]
* Добавить их в layout в котором они будут использоваться: **привести примеры кодом и вставить ссылку с описанием того, что для дефолтных ActionView надо использовать строго заданные ID**
* Если вам надо получить доступ к одной из ActionView внутри вашего Activity/Fragment, то вы можете сделать это
    * через import с заменой имени, если вы используете kotlin-android-extensions:  
`import kotlinx.android.synthetic.main.fr_gifts.contentView as recyclerView`**тут вопрос будет ли это работать с библиотекой? если нет, то написать о баге в kotlinx**  
    * через переменные(они проинициализированы в ActionsActivity/ActionsFragment): `contentActionView`, `loadingActionView`, `noInternetActionView`, `emptyContentActionView`, `errorActionView`  
При использовании данного способа, сейчас не представляется возможности изменить название переменных. Если у вас есть предложения, как это сделать, буду рад выслушать.
* Добавить `.withActionViews` в ваш rx-поток: 
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
* Вы можете отказаться от наследования [ActionsActivity]/[ActionsFragment], но все равно вы будете обязаны наследовать вашу ViewModel от ActionsViewModel
* В вашей Activity/Fragment вы должны проинициализировать ваши ActionViews и подписаться на обновления тех ActionView, которые вам нужны, также как это сделано в [ActionsActivity]/[ActionsFragment]

**В чем плюсы от использования такого способа?**  
* Вы избавляетесь от строго заданных ID для ActionViews и можете использовать абсолютно любые

**В чем минусы от использования такого способа?**
* Вы должны инициализировать ваши ActionViews сами
* Вы должны подписываться на обновления руками, также как это сделано в [ActionsActivity]/[ActionsFragment]

## Дополнительная информация
* Базовые типы ActionViews
* Использование готовых реализаций ActionViews
* еще
* еще
* еще
* еще
* еще

## Что ещё умеет библиотека?
В библиотеке есть несколько полезных extension-методов, которые упростят разработку. Найти их вы можете [здесь][SupportMethods].

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
* создать кастомные view или использовать те, которые я уже создал для вас - **показать, что некоторые кастомные view я уже сделал за них. показать какие есть. показать как создавать кастомные view - в отдельный документ**
* добавить их в layout, в котором они будут использоваться:
* и не забыть указать им строгие id - **перечислить какие id для какой view надо использовать**
* проинициализировать ActionView в коде - **обратить внимание на баг с kotlinx, показать, что дефолтные actionView инициализировать не надо, их надо лишь получить и если надо, то заменить имя в импорте**
* и добавить .withActionViews в ваш rx поток: **показать пример кодом**
* **показать, какие вспомогательные классы и методы есть, описать их - в отдельный документ**
* **показать как использовать и добавлять кастомные логики - в отдельный документ**
* **показать, что надо наследоваться от ActionViews-классов, Fragment, Activity, ViewModel - в начало документа**

# Как это работает?


[ActionsActivity]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/activity/ActionsActivity.kt>
[ActionsFragment]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/fragment/ActionsFragment.kt>
[ActionsViewModel]: <https://github.com/tanchuev/ActionViews-ViewModel/blob/master/actionviews/src/main/java/com/tanchuev/actionviews/viewmodel/viewmodel/ActionsViewModel.kt>
[LoadingView]: <%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#loadingview>
[NoInternetView]: <%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#nointernetview>
[EmptyContentView]: <%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#emptycontentview>
[ErrorView]: <%D0%91%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#errorview>
[ContentView]: <D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8-%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2-ActionViews#contentview>
