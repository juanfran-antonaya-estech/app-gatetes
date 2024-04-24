package es.estech.myapplication.data.models.breeds


import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("weight") var weight: Weight = Weight(),
    @SerializedName("id") var id: String = "", // beng
    @SerializedName("name") var name: String = "", // Bengal
    @SerializedName("cfa_url") var cfaUrl: String? = "", // http://cfa.org/Breeds/BreedsAB/Bengal.aspx
    @SerializedName("vetstreet_url") var vetstreetUrl: String? = "", // http://www.vetstreet.com/cats/bengal
    @SerializedName("vcahospitals_url") var vcahospitalsUrl: String? = "", // https://vcahospitals.com/know-your-pet/cat-breeds/bengal
    @SerializedName("temperament") var temperament: String = "", // Alert, Agile, Energetic, Demanding, Intelligent
    @SerializedName("origin") var origin: String = "", // United States
    @SerializedName("country_codes") var countryCodes: String = "", // US
    @SerializedName("country_code") var countryCode: String = "", // US
    @SerializedName("description") var description: String = "", // Bengals are a lot of fun to live with, but they're definitely not the cat for everyone, or for first-time cat owners. Extremely intelligent, curious and active, they demand a lot of interaction and woe betide the owner who doesn't provide it.
    @SerializedName("life_span") var lifeSpan: String = "", // 12 - 15
    @SerializedName("indoor") var indoor: Int = 0, // 0
    @SerializedName("lap") var lap: Int? = 0, // 0
    @SerializedName("adaptability") var adaptability: Int = 0, // 5
    @SerializedName("affection_level") var affectionLevel: Int = 0, // 5
    @SerializedName("child_friendly") var childFriendly: Int = 0, // 4
    @SerializedName("cat_friendly") var catFriendly: Int? = 0, // 4
    @SerializedName("dog_friendly") var dogFriendly: Int = 0, // 5
    @SerializedName("energy_level") var energyLevel: Int = 0, // 5
    @SerializedName("grooming") var grooming: Int = 0, // 1
    @SerializedName("health_issues") var healthIssues: Int = 0, // 3
    @SerializedName("intelligence") var intelligence: Int = 0, // 5
    @SerializedName("shedding_level") var sheddingLevel: Int = 0, // 3
    @SerializedName("social_needs") var socialNeeds: Int = 0, // 5
    @SerializedName("stranger_friendly") var strangerFriendly: Int = 0, // 3
    @SerializedName("vocalisation") var vocalisation: Int = 0, // 5
    @SerializedName("bidability") var bidability: Int? = 0, // 3
    @SerializedName("experimental") var experimental: Int = 0, // 0
    @SerializedName("hairless") var hairless: Int = 0, // 0
    @SerializedName("natural") var natural: Int = 0, // 0
    @SerializedName("rare") var rare: Int = 0, // 0
    @SerializedName("rex") var rex: Int = 0, // 0
    @SerializedName("suppressed_tail") var suppressedTail: Int = 0, // 0
    @SerializedName("short_legs") var shortLegs: Int = 0, // 0
    @SerializedName("wikipedia_url") var wikipediaUrl: String = "", // https://en.wikipedia.org/wiki/Bengal_(cat)
    @SerializedName("hypoallergenic") var hypoallergenic: Int = 0, // 1
    @SerializedName("reference_image_id") var referenceImageId: String = "", // O3btzLlsO
    @SerializedName("alt_names") var altNames: String? = "" // Sacred Birman, Sacred Cat Of Burma
)