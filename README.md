# NilExample
A small example for getting started with NilLoader.

## A quick note
NilLoader is not a general purpose loader intended to replace Fabric or Forge.
If you're here, you should either have a specific use case (e.g. wanting to
patch mods on old versions with broken tooling) or just want to mess around with
a new toy for the sake of it.

Additionally, NilLoader is currently *experimental* and ***API/ABI stability is
not yet guaranteed***.

If you're okay with that, then cool. Let's continue.

## Steps to use the template
1. You must pick a unique nilmod ID and put it in build.gradle. You will then
	have to rename src/main/resources/modid.nilmod.css to use your ID.
2. You need to rename the package from com.example.nilexample to a package
	you have permission to use; see [this wizard](https://unascribed.com/old/javapkg.html)
	for help if you don't know what a good package name is.
3. You will probably want to replace LICENSE with something else, unless CC0
	Public Domain Dedication is what you want.

## How do I launch a development environment?
NilGradle does not currently (and likely never will) offer a dev environment. In
the future, NilLoader may gain the ability to be run a normal game in dev mode
and then be attached to an IDE for code hotswap. **You will have to restart the
game every time you make changes to your mod**.

## How do I decompile the game?
Pass `-Pnil.decompile` when running your initial Gradle command to generate a
sources jar. You may need to delete `build/nil`.

## Another note
NilGradle is very primitive at the moment, and can quite easily get confused.
If things don't make sense, then delete `build/nil`.
