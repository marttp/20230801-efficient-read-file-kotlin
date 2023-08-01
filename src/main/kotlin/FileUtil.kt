import net.datafaker.Faker
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Paths

object FileUtil {

    private var FILE_PATH = "data.csv"

    fun writingFile() {
        val faker = Faker()
        try {
            BufferedWriter(FileWriter(FILE_PATH)).use { writer ->
                repeat(100_000_000) {
                    val firstName = faker.name().firstName()
                    val lastName = faker.name().lastName()
                    val email = faker.internet().emailAddress()
                    writer.write("$firstName,$lastName,$email\n")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFileFull() {
        Files.readAllLines(Paths.get(FILE_PATH)).forEach {
            val values = it.split(",")
            println("First name: ${values[0]}")
            println("Last name: ${values[1]}")
            println("Email: ${values[2]}")
        }
    }

    fun readFile() {
        Files.newBufferedReader(Paths.get(FILE_PATH))
            .forEachLine { reader ->
                val values = reader.split(",")
                println("First name: ${values[0]}")
                println("Last name: ${values[1]}")
                println("Email: ${values[2]}")
            }
    }

    fun copyFile() {
        BufferedWriter(FileWriter("copy_data.csv")).use { writer ->
            Files.newBufferedReader(Paths.get(FILE_PATH)).copyTo(writer)
        }
    }
}