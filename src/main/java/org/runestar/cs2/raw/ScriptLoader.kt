package org.runestar.cs2.raw

import java.nio.ByteBuffer
import java.nio.file.Files
import java.nio.file.Path

interface ScriptLoader {

    fun load(id: Int): Script?

    class Binary(val dir: Path, val fileExtension: String) : ScriptLoader {

        override fun load(id: Int): Script? {
            val file = dir.resolve("$id$fileExtension")
            if (Files.notExists(file)) return null
            val buffer = ByteBuffer.wrap(Files.readAllBytes(file))
            return Script.read(buffer)
        }
    }
}