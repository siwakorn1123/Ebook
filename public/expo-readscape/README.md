# Readscape — Expo Production Guide & Architecture

โปรเจกต์นี้ได้รับการจัดเตรียมไฟล์และโครงสร้างเพื่อพร้อมสำหรับการนำไปรันและขึ้น Production ด้วย **Expo (React Native)** และ **EAS Build**

---

## 📁 โครงสร้างไฟล์ที่เตรียมไว้ (ใน `/public/expo-readscape`)

```text
public/expo-readscape/
├── package.json               # Dependencies ทั้งหมดสำหรับ Expo SDK 52
├── app.json                   # การตั้งค่าแอป, Scheme, Adaptive Icon, และ Bundle Identifier
├── eas.json                   # การตั้งค่า EAS Build (Development, Preview, Production)
├── data/
│   └── sampleBooks.ts         # Mock data หนังสือ, หมวดหมู่, และคอลเลกชันในรูปแบบ TypeScript
├── store/
│   └── useBookStore.ts        # Zustand Store (จัดการชั้นหนังสือ, ความคืบหน้า, เหรียญ)
├── app/
│   ├── (tabs)/
│   │   ├── index.tsx          # Home Screen (Hero Banner, กำลังอ่านต่อ, หนังสือขายดี)
│   │   └── bookshelf.tsx      # Bookshelf Screen (กรองสถานะ, คอลเลกชัน, เปิดอ่าน)
│   └── reader/
│       └── [id].tsx           # Immersive Reader (ปรับขนาดฟอนต์, สลับโหมดสี 3 ธีม, ความคืบหน้า)
```

---

## 🚀 ขั้นตอนการเริ่มต้นใช้งานโปรเจกต์ (Getting Started)

1. **คัดลอกโฟลเดอร์หรือสร้างโปรเจกต์ใหม่ด้วย Expo CLI**:
   ```bash
   npx create-expo-app@latest readscape-expo --template tabs
   ```

2. **ติดตั้ง Dependencies เพิ่มเติม**:
   ```bash
   cd readscape-expo
   npx expo install expo-image expo-linear-gradient zustand @expo/vector-icons
   ```

3. **รันในโหมด Development**:
   ```bash
   npx expo start
   ```
   - สแกน QR Code ผ่านแอป **Expo Go** บน iOS หรือ Android เพื่อทดสอบทันที

---

## 📦 การขึ้น Production ด้วย EAS Build (Android & iOS)

### 1. ติดตั้งและ Login EAS CLI
```bash
npm install -g eas-cli
eas login
eas project:init
```

### 2. ตรวจสอบการตั้งค่าใน `app.json`
- **Android Package**: `com.readscape.app`
- **iOS Bundle Identifier**: `com.readscape.app`

### 3. สั่ง Build สำหรับ Production

- **Android (Google Play .aab)**:
  ```bash
  eas build --platform android --profile production
  ```

- **iOS (App Store .ipa)**:
  ```bash
  eas build --platform ios --profile production
  ```

### 4. ส่งขึ้น Store (EAS Submit)
- **Google Play Store**:
  ```bash
  eas submit -p android
  ```
- **Apple App Store**:
  ```bash
  eas submit -p ios
  ```
