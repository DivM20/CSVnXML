import java.io.File
import kotlin.time.measureTime

class UI {
    fun Show():Boolean{
        println("Enter path to a file with scv/xml type")
        println("--To exit enter \"esc\"--" )
        val path= readln()
        val type=path.split('.').last()
        val Parser = Parser()

        try {
            when(type){
                "csv"->{
                    val iStream = File(path).inputStream()
                    val time= measureTime {
                        var Ads=Parser.CSVparse(iStream).toMutableList()
                        getStats(Ads)
                    }
                    println("Time:"+time)
                    return false

                }
                "xml"-> {
                    val iStream = File(path).inputStream()
                    val time= measureTime {
                        var Ads = Parser.XMLparse(iStream).toMutableList()
                        getStats(Ads)

                    }
                    println("Time:"+time)
                    return  false
                }
                "esc"->return true
                else->{
                    println("Wrong path or file type.")
                    return false
                }
            }
        }catch (e: Exception){
            println("Exception \"" + e.message + "\" occured")
            return false
        }

    }

    fun getStats(Ads:MutableList<Address>){
        val Analyzer = Analyzer()
        var AdsMap =HashMap<Address,Int>()
        Analyzer.clearDuplicates(Ads,AdsMap)
        printDuplicates(AdsMap)
        val FlsMap=Analyzer.getFloorsCount(Ads)
        printFloorsCount(FlsMap)
    }


    fun printDuplicates(AdsMap:HashMap<Address,Int>){
        println("Duplicates:")
        val NewAdsMap= AdsMap.filterValues { it>=2 } as HashMap<Address, Int>
        NewAdsMap.forEach{(key,value)->println("${key.city.padStart(20)}, ${key.street}, ${key.house}. Этажей: ${key.floors} | $value")}
        println()
    }

    fun printFloorsCount(FlsMap:HashMap<String,Array<Int>>){
        println("Floors Stats:")
        FlsMap.forEach{(key,value)->println("${key.padStart(20)}, Floors:  ${value.joinToString(" | ")}")}
    }
}