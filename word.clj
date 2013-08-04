(in-ns 'word)
(clojure.core/refer 'clojure.core)
(clojure.core/refer 'clojure.repl)

(def test-sentence "The cat in the hat lashes out at people he loves, killing them one and all")

(def stop-words
	#{"a" "in" "that" "for" "was" "is" "it" "the" "of" "and" "to" "he"})

(def token-regex #"\w+")

(defn to-lower-case [token-string]
	(.toLowerCase token-string))

(defn tokenize-string
	"Return a list of tokens in the string, optionally with a stop-list, which is a set
	of tokens that should be excluded or filtered out."
	([input-string]
		(map to-lower-case (re-seq token-regex input-string)))

	([input-string stop-word?]
		(filter (complement stop-word?) (tokenize-string input-string))))

(defn tokenize 
	"Read the contents of a file as a string and return the tokenized contents of the file,
	optionally filtering out the set of tokens in the set 'stop-word?'"
	([filename]
		(tokenize-string (slurp filename)))

	([filename stop-word?]
		(tokenize-string (slurp filename) stop-word?)))
