Tasks
-----

Создание Thread: 
[ConcurrentOutput](demo/ConcurrentOutput.java)

Состояние Нити:
[ThreadState](demo/ThreadState.java) 

Режим ожидания:
[Wget](demo/Wget.java) 
 
Остановка потока (Прерывание нити): 
[ConsoleProgress](demo/ConsoleProgress.java)

Остановка блокированной (ожидающей) нити:
[StoppingThread](demo/StoppingThread.java)

Пинг-Понг:
[Ping-Pong](pingpong)

Проблемы с многопоточностью (пример):
[RaceCondition](demo/RaceCondition.java)

Синхронизация общих ресурсов:
[Cache](demo/Cache.java)

Модель памяти Java, double-checked lock Singleton:
[NaiveSingleton](demo/NaiveSingleton.java), 
[DclSingleton](demo/DclSingleton.java)

JCIP. Настройка библиотеки:
[Count](demo/Count.java), CountTest

Thread без общих ресурсов:
[User](resources/User.java), [UserCache](resources/UserCache.java)

Visibility. Общий ресурс вне критической секции.  
[ParseFileInitial](synchr/ParseFileInitial.java), 
[ParseFileNew](synchr/ParseFileNew.java)

Класс хранилища пользователей UserStorage:
[User](userstorage/User.java),
[UserStorage](userstorage/UserStorage.java), UserStorageTest

Thread safe динамический список (SingleLockList в обновленном задании):
[ThreadSafeArrayList](list/ThreadSafeArrayList.java), ThreadSafeArrayListTest

Wait/Notify example:
[Latch](notification/Latch.java),
[RandomizedActor](notification/RandomizedActor.java),
[Closer](notification/Closer.java),
[Opener](notification/Opener.java),
[LatchDemo](notification/LatchDemo.java)

Управление нитью через wait:  
[CountBarrier](waitnotify/CountBarrier.java), CountBarrierTest

Реализация шаблона Producer/Consumer (реализация блокирующей очереди для шаблона):  
[BoundedBlockingQueue](producerconsumer/BoundedBlockingQueue.java), BoundedBlockingQueueTest

Обеспечить остановку потребителя:
[StopConsumer](stopconsumer/StopConsumer.java)

Junit тест для блокирующей очереди: BoundedBlockingQueueTest#whenTakeAllThenReceivesAll

CAS (Compare and Swap) операции:
[CasCount](cas/CasCount.java), CasCountTest

CAS stack (реализовать потокобезопасный стек использую Compare-and-Swap операции):
[CasStack](cas/CasStack.java), CasStackTest
