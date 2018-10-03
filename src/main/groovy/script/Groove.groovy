package script

class ContactG {
    def firstName
    def lastName
    def birthDate

    String toString() {
        "($firstName, $lastName, $birthDate)"
    }

    void esim(String a, int b){
        println(a)
    }
}

ContactG contact = new ContactG(firstName: 'Vahagn', lastName: 'Kostandyan', birthDate: new Date())

println contact

println contact.firstName + 42

contact.esim('fd', 5)

def names = ['a', 'b', 'c']
Closure closure = {it -> println 'hello ' + it}
names.each closure