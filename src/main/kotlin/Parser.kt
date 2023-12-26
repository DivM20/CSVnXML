import org.w3c.dom.Element
import java.io.File
import java.io.FileInputStream
import javax.xml.parsers.DocumentBuilderFactory

class Parser {
    fun CSVparse(iStream:FileInputStream): List<Address> {
        val reader = iStream.bufferedReader()
        val header = reader.readLine()

        return reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val (city,street,house,floor) = it.split(';', ignoreCase = false, limit = 4)
                Address(city.trim('"'),street.trim('"'),house.trim().toInt(),floor.trim().removeSurrounding("\"").toInt())
            }.toList()
    }

    fun XMLparse(iStream:FileInputStream): List<Address> {

        val Ads= emptyList<Address>().toMutableList()
        val builderFactory = DocumentBuilderFactory.newInstance()
        val docBuilder = builderFactory.newDocumentBuilder()

        val doc = docBuilder.parse(iStream)
        val AdElements=doc.getElementsByTagName("item")


        for(i in 0 until  AdElements.length){
            val building=AdElements.item(i) as Element
            Ads.add(Address(building.getAttribute("city"),building.getAttribute("street"),building.getAttribute("house").toInt(),building.getAttribute("floor").toInt()))
        }
        return Ads
    }

}