import pandas as pd
import joblib
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder

# Load dataset
data = pd.read_csv("fraud_insurance_claims.csv")

# Convert fraud label
data["fraud_reported"] = data["fraud_reported"].map({"Y": 1, "N": 0})

# Encode categorical columns
encoder = LabelEncoder()
for col in data.columns:
    if data[col].dtype == "object":
        data[col] = encoder.fit_transform(data[col])

# Selected features
feature_columns = [
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

X = data[feature_columns]
y = data["fraud_reported"]

# Split dataset
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

# Train model
model = RandomForestClassifier(
    n_estimators=300,
    class_weight="balanced",
    random_state=42
)

model.fit(X_train, y_train)

# Save model + feature names
metadata = {
    "model": model,
    "features": feature_columns
}

joblib.dump(metadata, "claimwatch_model.pkl")

print("✅ Model trained and saved successfully")