Шаги для запуска playwright codegen на Gradle проекте:

**Добавить Playwright в зависимости проекта:**
Убедитесь, что ваш build.gradle файл настроен для использования Playwright. Обычно это включает добавление Playwright и его зависимостей.

_Пример для Kotlin DSL:_

```
dependencies {
    testImplementation("com.microsoft.playwright:playwright:1.40.1")
}
```

Установить Playwright:
После добавления зависимости, необходимо установить Playwright. Выполните следующую команду в терминале из корневого каталога вашего проекта:
```
./gradlew playwrightInstall
```
**Запустить Playwright Codegen:**
Playwright предоставляет CLI (командную строку) для генерации кода. Используйте следующий скрипт для запуска codegen:

Создайте или обновите файл scripts/codegen.sh для Unix или scripts/codegen.bat для Windows.

_Для Unix-систем (codegen.sh):_
```
#!/bin/bash
./gradlew playwrightRun -Pargs="codegen https://example.com"
```

_Для Windows (codegen.bat):_

```
@echo off
gradlew playwrightRun -Pargs="codegen https://example.com"
```

В этом скрипте https://example.com замените на URL, который вы хотите использовать для записи кода.

**Настройка задачи Gradle для запуска codegen:**
Добавьте новую задачу в ваш build.gradle файл, чтобы запустить playwright codegen:

_Пример для Kotlin DSL:_

```
tasks.register<JavaExec>("playwrightRun") {
classpath = sourceSets.main.get().runtimeClasspath
mainClass.set("com.microsoft.playwright.CLI")
args = project.findProperty("args")?.toString()?.split(' ') ?: listOf()
}
```
**Запуск Codegen через Gradle:**
Используйте команду для запуска codegen через Gradle:

```
./gradlew playwrightRun -Pargs="codegen https://example.com"
```
После этого откроется браузер Playwright, и начнется запись всех действий. Сгенерированный код будет отображен в реальном времени.

**Пример структуры проекта:**

```
my-gradle-project/
│
├── build.gradle
├── settings.gradle
├── src/
│   └── main/
│       └── java/
│           └── ...
└── scripts/
└── codegen.sh

```