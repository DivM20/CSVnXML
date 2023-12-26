class Analyzer {

    fun clearDuplicates(list:MutableList<Address>, map:HashMap<Address,Int>){
        var adIterator =list.iterator()
        while (adIterator.hasNext()){
            val ad = adIterator.next()
            var count=map.get(ad)
            map.put(ad,if(count==null) 1 else (count+1))
            if(count != null){
                adIterator.remove()
            }
        }

    }

    fun getFloorsCount(Ads:List<Address>):HashMap<String,Array<Int>>{
        var FlsMap=HashMap<String,Array<Int>>()
        for(address in Ads){
            var fl=FlsMap.get(address.city)
            if(fl==null){
                fl= Array<Int>(5) { 0 }
            }
            fl[address.floors-1]+=1
            FlsMap.put(address.city,fl)

        }
        return FlsMap
    }
}