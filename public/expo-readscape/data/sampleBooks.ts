export interface Chapter {
  number: number;
  title: string;
  durationMinutes: number;
  snippet: string;
}

export interface Review {
  reviewerName: string;
  rating: number;
  dateText: string;
  comment: string;
}

export type ReadingStatus = 'READING' | 'UNREAD' | 'FINISHED';

export interface Book {
  id: string;
  title: string;
  subtitle?: string;
  author: string;
  publisher?: string;
  price: number;
  originalPrice?: number;
  discountPercent?: number;
  rating: number;
  reviewsCount: string;
  category: string;
  rank?: number;
  coverUrl: string;
  synopsis: string;
  pagesCount: number;
  isbn?: string;
  isInShelf?: boolean;
  shelfCategory?: string;
  status?: ReadingStatus;
  readProgress?: number;
  lastReadText?: string;
  timeRemaining?: string;
  isAudiobookAvailable?: boolean;
  chapters?: Chapter[];
  reviews?: Review[];
}

export interface ShelfFolder {
  name: string;
  bookCount: number;
  colorHex: string;
}

export const LOGO_URL = "https://lh3.googleusercontent.com/aida/AEtjO1WOsFlPHwxO9uus6e0vVTK7zLwu5hiOcDQW6nfHhhbsETxeMBVT28E6dEmV3E0j3l7zCt_i9L1krx2Z9l_tYV-5M8lLzh7iLRvrvZ17n3cIcCOLmrIeiA_CbHSSXtlm06q0cbah9Mr3CLsSAP9N-XOGK5NZgLiusp_lu2WE8chPlVbi1szuQwY-7PrFFHl7J1QsJvJuC3DO8KLg-q0Sf2F81jIox0PKBGJavag-Td3p2Q";
export const AVATAR_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuDV2PZcUhY8yHacYWLr-76K0yFYQeB9s7_ZYmQf2-x7tx9DoB2HlIKWn2za4dUvXc7vpSEyjqncONeEjpiFWNPGA_AA63D0J_ovR5bYO8n8CcPQPLdPxMPCWREmO-AA09_3NJLEe9ukPw70Nz37aymYYtIEOT8jfYIRtALZo71JRcv4TOgdKuHV9rI6CktFVkqlCQnNkCFKROcLXs8BpgeZqzytc_cgkjgjfzs3Emx-";

export const CATEGORIES = [
  "ทั้งหมด",
  "พัฒนาตนเอง",
  "นิยาย & วรรณกรรม",
  "ธุรกิจ & การเงิน",
  "เทคโนโลยี & AI",
  "จิตวิทยา"
];

export const CONTINUE_READING_BOOK: Book = {
  id: "continue-1",
  title: "The Psychology of Money",
  subtitle: "จิตวิทยาว่าด้วยเงิน",
  author: "มอร์แกน เฮาเซิล (Morgan Housel)",
  price: 265,
  originalPrice: 320,
  rating: 4.9,
  reviewsCount: "3.8k",
  category: "ธุรกิจ & การเงิน",
  coverUrl: "https://lh3.googleusercontent.com/aida-public/AB6AXuAsReCf-tqNqQLEU5on7bSO1uzLBtC5NRXlmqZeo2WtpVHn7XMk-e5DTWwDcxgOvKviRT62cmwGSwAqhSPPRgoWTtSZyZBSt1d4AKFbY7K3j8kGtKss_mtOgWfQYE7tTYeY_LPil3JFIxQvOQZH5c7oBR0LyE1Uh4CEdyw8CrjQbHR9spoo7s79MQMLOhquIExU5AWOmCEwtA62i2vCcXME55UkhUzu3uhKGAb0UXGT",
  synopsis: "ความสำเร็จทางการเงินไม่ได้ขึ้นอยู่กับว่าคุณฉลาดแค่ไหน แต่ขึ้นอยู่กับพฤติกรรมของคุณต่างหาก การจัดการเรื่องเงินไม่ใช่เรื่องของคณิตศาสตร์หรือสูตรคำนวณที่ตายตัว แต่มันเป็นเรื่องของจิตวิทยา อารมณ์ และประวัติศาสตร์ส่วนบุคคลที่หล่อหลอมมุมมองของเราต่อความมั่งคั่งและความเสี่ยง",
  pagesCount: 280,
  isbn: "978-616-93528-0-7",
  isInShelf: true,
  shelfCategory: "คัดสรรสำหรับทำงาน",
  status: 'READING',
  readProgress: 68,
  lastReadText: "บทที่ 14 • อ่านล่าสุดเมื่อวาน",
  timeRemaining: "เหลืออีกประมาณ 42 นาที",
  chapters: [
    { number: 1, title: "ไม่มีใครบ้า (No One's Crazy)", durationMinutes: 15, snippet: "ประสบการณ์ของคุณกับเงินอาจคิดเป็นเพียง 0.00000001% ของสิ่งที่เกิดขึ้นในโลก แต่คิดเป็น 80% ของวิธีที่คุณคิดว่าโลกทำงาน" },
    { number: 2, title: "โชคและความเสี่ยง (Luck & Risk)", durationMinutes: 18, snippet: "ไม่มีอะไรดีหรือแย่อย่างที่เห็นในตอนแรก โชคและความเสี่ยงเป็นพี่น้องกันที่ผลัดกันทำงานในชีวิตของคุณ" },
    { number: 14, title: "คุณจะเปลี่ยนแปลงไป (You'll Change)", durationMinutes: 22, snippet: "การวางแผนระยะยาวเป็นเรื่องยากเพราะเป้าหมายและความต้องการของมนุษย์เปลี่ยนแปลงไปตามกาลเวลา" }
  ],
  reviews: [
    { reviewerName: "ธนัท วัฒนโภคิน", rating: 5.0, dateText: "3 วันที่แล้ว", comment: "หนังสือเปลี่ยนวิธีคิดเรื่องเงินที่ดีที่สุดเล่มหนึ่ง อ่านเข้าใจง่ายและนำมาปรับใช้กับพอร์ตลงทุนได้ทันที" },
    { reviewerName: "แพรวา เลิศศิริ", rating: 5.0, dateText: "1 สัปดาห์ที่แล้ว", comment: "เปิดมุมมองเรื่องความพึงพอใจในชีวิตและการออมเงินอย่างมีความสุข แนะนำทุกคนควรอ่าน" }
  ]
};

export const BEST_SELLERS: Book[] = [
  {
    id: "best-1",
    title: "จิตวิทยาแห่งการตัดสินใจ (Atomic Thinking)",
    subtitle: "ปลดล็อกศักยภาพสมองผ่านการตัดสินใจย่อย",
    author: "ดร. นภนต์ คชวัชระ",
    publisher: "สำนักพิมพ์ Readscape",
    price: 219,
    originalPrice: 350,
    discountPercent: 37,
    rating: 4.9,
    reviewsCount: "1.2k",
    category: "พัฒนาตนเอง",
    rank: 1,
    coverUrl: "https://lh3.googleusercontent.com/aida-public/AB6AXuDBJOtleBq89uOSRfTnyrGpWwm4ExlIxSBNSUUei-_wIyRRsv0822D_rdKvvYSj5YSjrHHzqWDbu6l1Ja12nOL3_zbkamQ_wtq_YnTxx_niVFr2ncPfw-8PyMJJbD9x6bY5ZpqGJ4X8P6twuGsWez1lvDEciR1ydvBNqCgDUOsloh_d0pAl-mWgR1sc0rwNuAfVZtMTRSXnZcuSi0O6i9hbyFwMo9kJwuoftiPYJotK",
    synopsis: "ในยุคที่มีข้อมูลท่วมท้น การตัดสินใจที่ถูกต้องกลายเป็นทักษะที่มีค่าที่สุด หนังสือเล่มนี้สรุปงานวิจัยพฤติกรรมศาสตร์และประสาทวิทยาศาสตร์มาเป็นแบบจำลองการคิดขนาดเล็ก (Micro-models) ที่คุณสามารถนำไปใช้ได้ทันที",
    pagesCount: 264,
    isbn: "978-616-88210-3-1"
  },
  {
    id: "best-2",
    title: "ปีแสงระหว่างเรา (Light Years Apart)",
    subtitle: "นวนิยายไซไฟรางวัลยอดเยี่ยม",
    author: "กานต์ชนก สุริยาวงศ์",
    price: 285,
    originalPrice: 380,
    discountPercent: 25,
    rating: 4.8,
    reviewsCount: "890",
    category: "นิยาย & วรรณกรรม",
    rank: 2,
    coverUrl: "https://lh3.googleusercontent.com/aida-public/AB6AXuA7yX_876oH_bQ7jXf3s1fO3V8xL5iH3n7t5e1s2g",
    synopsis: "เรื่องราวความรักข้ามกาลอวกาศ เมื่อการเดินทางผ่านรูหนอนทำให้เวลาของคนสองคนเดินไม่เท่ากัน หนึ่งคนแก่ตัวลงในขณะที่อีกคนยังคงเยาว์วัย",
    pagesCount: 340
  },
  {
    id: "best-3",
    title: "AI First Organization",
    subtitle: "กลยุทธ์ขับเคลื่อนองค์กรด้วยปัญญาประดิษฐ์",
    author: "วรวุฒิ นิติวรางกูร",
    price: 320,
    rating: 4.7,
    reviewsCount: "640",
    category: "เทคโนโลยี & AI",
    rank: 3,
    coverUrl: "https://lh3.googleusercontent.com/aida-public/AB6AXuDBJOtleBq89uOSRfTnyrGpWwm4ExlIxSBNSUUei-_wIyRRsv0822D_rdKvvYSj5YSjrHHzqWDbu6l1Ja12nOL3_zbkamQ_wtq_YnTxx_niVFr2ncPfw-8PyMJJbD9x6bY5ZpqGJ4X8P6twuGsWez1lvDEciR1ydvBNqCgDUOsloh_d0pAl-mWgR1sc0rwNuAfVZtMTRSXnZcuSi0O6i9hbyFwMo9kJwuoftiPYJotK",
    synopsis: "คู่มือผู้บริหารสำหรับการทรานส์ฟอร์มองค์กรด้วย GenAI และ Machine Learning พร้อมกรณีศึกษาจากบริษัทชั้นนำในเอเชียตะวันออกเฉียงใต้",
    pagesCount: 310
  }
];

export const SHELF_FOLDERS: ShelfFolder[] = [
  { name: "คัดสรรสำหรับทำงาน", bookCount: 14, colorHex: "#C85A32" },
  { name: "อ่านวันหยุด", bookCount: 8, colorHex: "#4A7C59" },
  { name: "นิยาย & ไซไฟ", bookCount: 19, colorHex: "#2C3E50" }
];
