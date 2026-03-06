import pandas as pd
import joblib
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder

data = pd.read_csv("insurance_claims.csv")

# convert fraud label
data["fraud_reported"] = data["fraud_reported"].map({"Y":1,"N":0})

# encode text columns
encoder = LabelEncoder()

for col in data.columns:
    if data[col].dtype == "object":
        data[col] = encoder.fit_transform(data[col])

# select important features
features = [
    "months_as_customer",
    "age",
    "policy_deductable",
    "policy_annual_premium",
    "umbrella_limit",
    "incident_severity",
    "number_of_vehicles_involved",
    "bodily_injuries",
    "total_claim_amount"
]

X = data[features]
y = data["fraud_reported"]

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

model = RandomForestClassifier(n_estimators=200)

model.fit(X_train, y_train)

joblib.dump(model, "claimwatch_model.pkl")

print("Model trained successfully")