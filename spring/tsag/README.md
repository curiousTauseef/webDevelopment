##Issues need to remember :)


####Spring considers that anything behind the last dot is a file extension 
- use: `/somepath/{variable:.+}`
to mapping parameter include .extension

####need default constructor in model for REST to build object from Json string (when geting response from server and use spring.rest method to get object)

##- hascode()
###- equals()
The theory (for the language lawyers and the mathematically inclined):

> equals() (javadoc) must define an equivalence relation (it must be reflexive, symmetric, and transitive). In addition, it must be consistent (if the objects are not modified, then it must keep returning the same value). Furthermore, o.equals(null) must always return false.

- hashCode() (javadoc) must also be consistent (if the object is not modified in terms of equals(), it must keep returning the same value).

The relation between the two methods is:

> Whenever a.equals(b), then a.hashCode() must be same as b.hashCode().

- hascode() & equals() : http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java?rq=1

> hashCode() is used for bucketing in Hash implementations like HashMap, HashTable, HashSet, etc.

> The value received from hashCode() is used as the bucket number for storing elements of the set/map. This bucket number is the address of the element inside the set/map.

> When you do contains() it will take the hash code of the element, then look for the bucket where hash code points to. If more than 1 element is found in the same bucket (multiple objects can have the same hash code), then it uses the equals() method to evaluate if the objects are equal, and then decide if contains() is true or false, or decide if element could be added in the set or not.
