package com.example.data

import com.example.model.Book
import com.example.model.Chapter
import com.example.model.ReadingStatus
import com.example.model.Review
import com.example.model.ShelfFolder

object SampleBooksData {

    // Hotlinked URLs from HTML snippet & user uploads
    const val LOGO_URL = "https://lh3.googleusercontent.com/aida/AEtjO1WOsFlPHwxO9uus6e0vVTK7zLwu5hiOcDQW6nfHhhbsETxeMBVT28E6dEmV3E0j3l7zCt_i9L1krx2Z9l_tYV-5M8lLzh7iLRvrvZ17n3cIcCOLmrIeiA_CbHSSXtlm06q0cbah9Mr3CLsSAP9N-XOGK5NZgLiusp_lu2WE8chPlVbi1szuQwY-7PrFFHl7J1QsJvJuC3DO8KLg-q0Sf2F81jIox0PKBGJavag-Td3p2Q"
    const val AVATAR_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuDV2PZcUhY8yHacYWLr-76K0yFYQeB9s7_ZYmQf2-x7tx9DoB2HlIKWn2za4dUvXc7vpSEyjqncONeEjpiFWNPGA_AA63D0J_ovR5bYO8n8CcPQPLdPxMPCWREmO-AA09_3NJLEe9ukPw70Nz37aymYYtIEOT8jfYIRtALZo71JRcv4TOgdKuHV9rI6CktFVkqlCQnNkCFKROcLXs8BpgeZqzytc_cgkjgjfzs3Emx-"

    val continueReadingBook = Book(
        id = "continue-1",
        title = "The Psychology of Money",
        subtitle = "จิตวิทยาว่าด้วยเงิน",
        author = "มอร์แกน เฮาเซิล (Morgan Housel)",
        price = 265,
        originalPrice = 320,
        rating = 4.9f,
        reviewsCount = "3.8k",
        category = "ธุรกิจ & การเงิน",
        coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAsReCf-tqNqQLEU5on7bSO1uzLBtC5NRXlmqZeo2WtpVHn7XMk-e5DTWwDcxgOvKviRT62cmwGSwAqhSPPRgoWTtSZyZBSt1d4AKFbY7K3j8kGtKss_mtOgWfQYE7tTYeY_LPil3JFIxQvOQZH5c7oBR0LyE1Uh4CEdyw8CrjQbHR9spoo7s79MQMLOhquIExU5AWOmCEwtA62i2vCcXME55UkhUzu3uhKGAb0UXGT",
        synopsis = "ความสำเร็จทางการเงินไม่ได้ขึ้นอยู่กับว่าคุณฉลาดแค่ไหน แต่ขึ้นอยู่กับพฤติกรรมของคุณต่างหาก การจัดการเรื่องเงินไม่ใช่เรื่องของคณิตศาสตร์หรือสูตรคำนวณที่ตายตัว แต่มันเป็นเรื่องของจิตวิทยา อารมณ์ และประวัติศาสตร์ส่วนบุคคลที่หล่อหลอมมุมมองของเราต่อความมั่งคั่งและความเสี่ยง",
        pagesCount = 280,
        isbn = "978-616-93528-0-7",
        isInShelf = true,
        shelfCategory = "คัดสรรสำหรับทำงาน",
        status = ReadingStatus.READING,
        readProgress = 68,
        lastReadText = "บทที่ 14 • อ่านล่าสุดเมื่อวาน",
        timeRemaining = "เหลืออีกประมาณ 42 นาที",
        chapters = listOf(
            Chapter(1, "ไม่มีใครบ้า (No One's Crazy)", 15, "ประสบการณ์ของคุณกับเงินอาจคิดเป็นเพียง 0.00000001% ของสิ่งที่เกิดขึ้นในโลก แต่คิดเป็น 80% ของวิธีที่คุณคิดว่าโลกทำงาน"),
            Chapter(2, "โชคและความเสี่ยง (Luck & Risk)", 18, "ไม่มีอะไรดีหรือแย่อย่างที่เห็นในตอนแรก โชคและความเสี่ยงเป็นพี่น้องกันที่ผลัดกันทำงานในชีวิตของคุณ"),
            Chapter(14, "คุณจะเปลี่ยนแปลงไป (You'll Change)", 22, "การวางแผนระยะยาวเป็นเรื่องยากเพราะเป้าหมายและความต้องการของมนุษย์เปลี่ยนแปลงไปตามกาลเวลา")
        ),
        reviews = listOf(
            Review("ธนัท วัฒนโภคิน", 5.0f, "3 วันที่แล้ว", "หนังสือเปลี่ยนวิธีคิดเรื่องเงินที่ดีที่สุดเล่มหนึ่ง อ่านเข้าใจง่ายและนำมาปรับใช้กับพอร์ตลงทุนได้ทันที"),
            Review("แพรวา เลิศศิริ", 5.0f, "1 สัปดาห์ที่แล้ว", "เปิดมุมมองเรื่องความพึงพอใจในชีวิตและการออมเงินอย่างมีความสุข แนะนำทุกคนควรอ่าน")
        )
    )

    val bestSellers = listOf(
        Book(
            id = "best-1",
            title = "จิตวิทยาแห่งการตัดสินใจ (Atomic Thinking)",
            subtitle = "ปลดล็อกศักยภาพสมองผ่านการตัดสินใจย่อย",
            author = "ดร. นภนต์ คชวัชระ",
            publisher = "สำนักพิมพ์ Readscape",
            price = 219,
            originalPrice = 350,
            discountPercent = 37,
            rating = 4.9f,
            reviewsCount = "1.2k",
            category = "พัฒนาตนเอง",
            rank = 1,
            coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDBJOtleBq89uOSRfTnyrGpWwm4ExlIxSBNSUUei-_wIyRRsv0822D_rdKvvYSj5YSjrHHzqWDbu6l1Ja12nOL3_zbkamQ_wtq_YnTxx_niVFr2ncPfw-8PyMJJbD9x6bY5ZpqGJ4X8P6twuGsWez1lvDEciR1ydvBNqCgDUOsloh_d0pAl-mWgR1sc0rwNuAfVZtMTRSXnZcuSi0O6i9hbyFwMo9kJwuoftiPYJotK",
            synopsis = "ในยุคที่มีข้อมูลท่วมท้น การตัดสินใจที่ถูกต้องกลายเป็นทักษะที่มีค่าที่สุด หนังสือเล่มนี้สรุปงานวิจัยพฤติกรรมศาสตร์และประสาทวิทยาศาสตร์มาเป็นแบบจำลองการคิดขนาดเล็ก (Micro-models) ที่คุณสามารถนำไปใช้ได้ทันทีในชีวิตประจำวันและการทำงาน เพื่อลดความเหนื่อยล้าในการตัดสินใจและเพิ่มผลลัพธ์ที่ดีขึ้น",
            pagesCount = 264,
            isbn = "978-616-88210-3-1",
            chapters = listOf(
                Chapter(1, "จุดกำเนิดของความคิดอะตอม", 12, "ทำไมการตัดสินใจย่อยๆ 100 ครั้งต่อวันจึงสร้างผลลัพธ์ที่เปลี่ยนชีวิตได้มากกว่าการตัดสินใจครั้งใหญ่เพียงครั้งเดียว"),
                Chapter(2, "ลดภาระการประมวลผลของสมอง", 16, "กลยุทธ์จำกัดตัวเลือกและสร้างระบบอัตโนมัติในการทำงาน"),
                Chapter(3, "กรอบการมองความน่าจะเป็น", 20, "ก้าวข้ามอคติความมั่นใจเกินไปและการประเมินความเสี่ยงที่แม่นยำ")
            ),
            reviews = listOf(
                Review("ชานนท์ สันติพงศ์", 5.0f, "เมื่อวาน", "หนังสือเขียนกระชับ ตรงประเด็น ไม่มีน้ำ อ่านจบใน 2 วันแล้วเริ่มจัดระเบียบการทำงานใหม่เลยครับ"),
                Review("กานต์พิชชา", 4.8f, "4 วันก่อน", "ชอบบทที่พูดเรื่อง Decision Fatigue มาก มีประโยชน์สุดๆ คุ้มค่าราคามาก")
            )
        ),
        Book(
            id = "best-2",
            title = "คิดแบบผู้นำแห่งอนาคต",
            subtitle = "กลยุทธ์และทักษะแห่งศตวรรษที่ 21",
            author = "ภาณุวัฒน์ กลิ่นสุข",
            publisher = "สำนักพิมพ์ Readscape",
            price = 245,
            originalPrice = 329,
            discountPercent = 25,
            rating = 4.8f,
            reviewsCount = "890",
            category = "ธุรกิจ & การเงิน",
            rank = 2,
            coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDViH76dnPMalvOWbnzrO52mBPytowujJzF75J6hXd5oqvqQU4OyduKjjSc7pe1Dedi8QdgJ7cySarhysgvR1lPOHjcTHc9mJo4IfWNeyrWFDY5l-HWvZPTDMSCjNAVtGytQagxTUywjbN020--waQOTdhUz80Ku9SRkP3eDG7FR9Gj4HTGx_BVXQNPeMkHFf5QLQ9p-Kr9tU0pdh6CgTlqhKu3SctA19MYESasBRWE",
            synopsis = "ผู้นำยุคใหม่ไม่ใช่ผู้ที่รู้คำตอบทุกอย่าง แต่คือผู้ที่ตั้งคำถามที่ทรงพลังที่สุดและสร้างพื้นที่ปลอดภัยให้ทีมงานกล้าทดลองและล้มเหลว เรียนรู้แก่นแท้ของผู้นำที่ปรับตัวได้ในสภาพแวดล้อมที่ซับซ้อนและเปลี่ยนแปลงฉับพลัน",
            pagesCount = 252,
            isbn = "978-616-77341-9-5",
            chapters = listOf(
                Chapter(1, "การเป็นผู้นำแบบรับฟัง (Empathetic Leadership)", 15, "พลังแห่งความเข้าอกเข้าใจในยุคที่ทุกอย่างขับเคลื่อนด้วย AI"),
                Chapter(2, "ความคล่องตัวเชิงกลยุทธ์", 20, "ปรับทิศทางองค์กรอย่างรวดเร็วโดยไม่สูญเสียเป้าหมายหลัก")
            ),
            reviews = listOf(
                Review("วริศรา เจริญชัย", 5.0f, "5 วันก่อน", "เหมาะมากสำหรับผู้บริหารรุ่นใหม่และหัวหน้าทีมที่กำลังรับมือกับการเปลี่ยนแปลงทางเทคโนโลยี")
            )
        ),
        Book(
            id = "best-3",
            title = "นิยาย: ลมหายใจแห่งกาลเวลา",
            subtitle = "วรรณกรรมซาบซึ้งใจว่าด้วยความทรงจำและการเยียวยา",
            author = "จิดานันท์ พิมพา",
            publisher = "สำนักพิมพ์ Readscape",
            price = 199,
            originalPrice = 285,
            discountPercent = 30,
            rating = 4.95f,
            reviewsCount = "2.4k",
            category = "นิยาย & วรรณกรรม",
            rank = 3,
            coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCzVU4XAaCP9v2bkzcVKr1lnmdD__xZjwbHsRs9rqxk1zQze7Q456H2tSwVwly9kimNs3A7-WwMA0u3QwmdyIPZI9W8X-uu6vdpQt5Ue8uo6REfkjkdetgiZeM0uaiI8xMWPZEHL3GKzWNyNTpXWx6rGjSM2HLN0-CYVBdgLmEsz6OeNOg9dNUSFKbmeKNjRXLBCcIvajgN8wP_ZgymDhCoPQsNdjUqDikPHSnnejWl",
            synopsis = "เรื่องราวของร้านหนังสือเล็กๆ ริมชายฝั่งทะเลที่เปิดเฉพาะช่วงเวลาพลบค่ำ ผู้คนที่แวะเวียนเข้ามาไม่ได้มาเพื่อซื้อหนังสือ แต่มาเพื่อพบกับเรื่องราวที่กาลเวลาได้เก็บซ่อนไว้ นวนิยายละมุนละไมที่จะโอบกอดหัวใจที่อ่อนล้าในทุกหน้ากระดาษ",
            pagesCount = 310,
            isbn = "978-616-55412-2-0",
            chapters = listOf(
                Chapter(1, "เวลาพลบค่ำที่ริมทะเล", 18, "สายลมเค็มและกลิ่นกระดาษเก่าที่ลอยอบอวล"),
                Chapter(2, "จดหมายที่ไม่เคยส่งถึง", 22, "หน้าที่ว่างเปล่ากับหมึกที่จางหายไปตามกาลเวลา")
            ),
            reviews = listOf(
                Review("พิชชาภา สุขเจริญ", 5.0f, "สัปดาห์ก่อน", "อ่านแล้วน้ำตาซึม ภาษาไพเราะราวกับบทกวี ประทับใจมากค่ะ")
            )
        ),
        Book(
            id = "best-4",
            title = "ศิลปะแห่งความเงียบ ในโลกที่เสียงดัง",
            subtitle = "The Art of Silence in a Noisy World",
            author = "โทโมกะ ชิบาตะ (แปลโดย รพีพร มุ่งมั่น)",
            publisher = "สำนักพิมพ์ Readscape",
            price = 199,
            originalPrice = 280,
            discountPercent = 28,
            rating = 4.9f,
            reviewsCount = "1.4k",
            category = "จิตวิทยา",
            coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAsReCf-tqNqQLEU5on7bSO1uzLBtC5NRXlmqZeo2WtpVHn7XMk-e5DTWwDcxgOvKviRT62cmwGSwAqhSPPRgoWTtSZyZBSt1d4AKFbY7K3j8kGtKss_mtOgWfQYE7tTYeY_LPil3JFIxQvOQZH5c7oBR0LyE1Uh4CEdyw8CrjQbHR9spoo7s79MQMLOhquIExU5AWOmCEwtA62i2vCcXME55UkhUzu3uhKGAb0UXGT",
            synopsis = "ศิลปะแห่งความเงียบในโลกที่เสียงดัง โซเชียลมีเดีย ความคิดเห็น การแจ้งเตือนที่ไม่มีวันหยุดพัก หนังสือเล่มนี้จะพาคุณค้นพบความสงบจากภายในและการเชื่อมโยงกับตัวเองอย่างลึกซึ้ง",
            pagesCount = 210,
            isbn = "978-616-44390-1-6",
            chapters = listOf(
                Chapter(1, "มลพิษทางเสียงในจิตใจ", 14, "เราเคยชินกับความวุ่นวายจนลืมไปว่าความเงียบมีค่าเพียงใด"),
                Chapter(2, "สร้างเกาะแห่งความเงียบ", 16, "ฝึกสร้างช่วงเวลาสงบสุขวันละ 20 นาที")
            )
        ),
        Book(
            id = "best-5",
            title = "พลังแห่งนิสัย (Atomic Habits: Thai Edition)",
            subtitle = "เปลี่ยนชีวิตด้วยพลังแห่งการปรับปรุง 1%",
            author = "เจมส์ เคลียร์ (James Clear)",
            publisher = "สำนักพิมพ์ Readscape",
            price = 220,
            originalPrice = 320,
            discountPercent = 31,
            rating = 4.7f,
            reviewsCount = "5.1k",
            category = "พัฒนาตนเอง",
            coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAbT4Y2WxC2_xYwo0LVJwaZoGPtqB7pi-nsaffLV4T5fjZgMvxuX-wVXjCxuNCMiu9FkJoz3yKbiJsSRh-322dU-OXsyiXWxVA6GAux8mEyy3rnUGsey11FQ5nP2_pxUiXoxSYnEWLmian6VKeTtmOHJcUTBF4ORgepVQU-gnwvUhYCWy80XNTmVfRVPg-g7JXor1zwnDr74nSmV_5MvHT9bPd6b5yvfpx-n6XBN8gc",
            synopsis = "การเปลี่ยนแปลงเล็กๆ น้อยๆ ที่คุณทำอย่างสม่ำเสมอทุกวัน จะส่งผลลัพธ์มหาศาลแบบทวีคูณ หนังสือเล่มนี้ให้ขั้นตอนปฏิบัติ 4 ขั้นตอนที่ผ่านการทดสอบทางวิทยาศาสตร์ เพื่อสร้างนิสัยที่ดีและทำลายนิสัยที่ไม่ดี",
            pagesCount = 336,
            isbn = "978-616-28734-3-0",
            chapters = listOf(
                Chapter(1, "พลังอันน่าทึ่งของนิสัยจิ๋ว", 15, "ทำไมการปรับปรุงวันละ 1% ถึงทำให้คุณดีขึ้น 37 เท่าใน 1 ปี"),
                Chapter(2, "กฎ 4 ข้อเพื่อสร้างนิสัยใหม่", 20, "ทำให้ชัดเจน ทำให้น่าดึงดูด ทำให้ง่าย และทำให้พึงพอใจ")
            )
        )
    )

    val recommendedBooks = listOf(
        Book(
            id = "rec-1",
            title = "The Art of Deep Focus: ศิลปะการโฟกัสลึก",
            subtitle = "คืนสมาธิและประสิทธิภาพสูงสุดในยุคแห่งสิ่งรบกวน",
            author = "กฤษณ์ รัตนวารี",
            publisher = "สำนักพิมพ์ Readscape",
            price = 259,
            originalPrice = 310,
            rating = 4.85f,
            reviewsCount = "640",
            category = "จิตวิทยา",
            matchPercent = 96,
            coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuA0MjNdDDOxJH2pvQCOBlOsxLQIfp54o7rQwbbsqw3529aJsrlY4EfkLjW9czfv7jyFtTN2lS7QesKI9eLmElpL9RcIjI49cLZi2mXrKlp4GiAsgXbuj41K3GWHxs66_J-vTvKRmCG4Gl9LtysH26GuZz8iGQ5vFDCkAnZZjarlB4Knms-sjmA6TAlqP3P2GlF2SgMb4ildutHpDNp1jHT9b7YdB1nA0NnALnAfoS_L",
            synopsis = "ในโลกที่ทุกแอปพลิเคชันพยายามแย่งชิงความสนใจของคุณ ความสามารถในการทำงานแบบมีสมาธิลึก (Deep Work) คืออภิมหาอำนาจ หนังสือเล่มนี้แนะแนวทางปฏิบัติและเทคนิคที่ช่วยให้คุณเข้าสู่สภาวะ Flow State ได้อย่างง่ายดายและรักษาไว้อย่างต่อเนื่อง",
            pagesCount = 240,
            isbn = "978-616-99381-0-4",
            chapters = listOf(
                Chapter(1, "สวะแห่งความสนใจ (Attention Residue)", 12, "ทำไมการสลับหน้าจอจึงทำลายประสิทธิภาพสมองของคุณ"),
                Chapter(2, "พิธีกรรมเข้าสู่ Flow State", 18, "สร้างสภาพแวดล้อมที่ไร้สิ่งดึงดูดใจและเริ่มงานได้ทันที")
            ),
            reviews = listOf(
                Review("ปิยบุตร ศิริวัฒน์", 5.0f, "2 วันก่อน", "ช่วยชีวิตคนทำงานที่มีปัญหาเรื่องสมาธิสั้นลงเพราะมือถืออย่างผมมากครับ แนะนำเลย!")
            )
        ),
        Book(
            id = "rec-2",
            title = "Artificial Intuition: มนุษย์ในยุคสมองกล",
            subtitle = "การผสานสัญชาตญาณมนุษย์เข้ากับปัญญาประดิษฐ์",
            author = "ดร. สุขุม นิมมานเหมินท์",
            publisher = "สำนักพิมพ์ Readscape",
            price = 280,
            originalPrice = 390,
            rating = 4.9f,
            reviewsCount = "720",
            category = "เทคโนโลยี",
            matchPercent = 92,
            coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuB_k9_MTVPunfHQQzrMV0o2bntpLLobOqpx1rVVBnczdT4aiee-pyrNtI2JyfGIR6bnSgjaGCSsZFuxTCez5clf85njRcBtya8D2Phcbi_-mR2e2yTNwZPd8vgZZNdza5ZRjD3LIGlBBR7FX1zeRyB8raSZGQS7f9SUrPcIhaIC4lTtYZVHc48YwSORiqppoIIulwPz6-U3UG_CZwhUzJKzHyOPbfBVLMxiLTPcXnJW",
            synopsis = "เมื่ออัลกอริทึมสามารถคิดคำนวณและสร้างสรรค์ผลงานได้เหนือกว่ามนุษย์ในหลายมิติ สิ่งใดคือคุณค่าที่แท้จริงของความเป็นมนุษย์? หนังสือสำรวจปรัชญาและเทคโนโลยีเพื่อหาคำตอบว่าเราจะร่วมมือกับ AI อย่างไรให้เกิดปัญญาสูงสุด",
            pagesCount = 295,
            isbn = "978-616-60911-5-8",
            chapters = listOf(
                Chapter(1, "ขอบเขตสุดท้ายของจิตสำนึก", 20, "ความแตกต่างระหว่างการประมวลผลข้อมูลและความเข้าใจที่แท้จริง"),
                Chapter(2, "ความฉลาดร่วมระหว่างคนและเครื่องจักร", 25, "วิธีทำงานร่วมกับ Generative AI อย่างมืออาชีพ")
            ),
            reviews = listOf(
                Review("ณัชชา พงษ์ศิริ", 5.0f, "1 สัปดาห์ก่อน", "เขียนได้ลึกซึ้งและชวนขบคิดมาก ไม่ใช่แค่หนังสือสอนใช้ AI ทั่วไป")
            )
        )
    )

    // Books shown in Image 4 (Book Details) and Image 8 (Bookshelf)
    val detailsFeaturedBook = Book(
        id = "detail-leader",
        title = "คิดแบบผู้นำไร้ตำแหน่ง",
        subtitle = "Leadership without a Title - Unlock Your Potential from Within",
        author = "โรบิน ชาร์มา (Robin Sharma) • แปลโดย สันติราษฎร์",
        publisher = "สำนักพิมพ์ Readscape",
        price = 219,
        originalPrice = 290,
        discountPercent = 24,
        rating = 4.8f,
        reviewsCount = "1.4k",
        category = "การพัฒนาตนเอง",
        coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDViH76dnPMalvOWbnzrO52mBPytowujJzF75J6hXd5oqvqQU4OyduKjjSc7pe1Dedi8QdgJ7cySarhysgvR1lPOHjcTHc9mJo4IfWNeyrWFDY5l-HWvZPTDMSCjNAVtGytQagxTUywjbN020--waQOTdhUz80Ku9SRkP3eDG7FR9Gj4HTGx_BVXQNPeMkHFf5QLQ9p-Kr9tU0pdh6CgTlqhKu3SctA19MYESasBRWE",
        synopsis = "คุณไม่จำเป็นต้องมีตำแหน่งใหญ่โตหรือห้องทำงานส่วนตัวเพื่อที่จะเป็นผู้นำ ไม่ว่าคุณจะอยู่ในบทบาทใดในองค์กรหรือชีวิต การแสดงความเป็นเลิศ ความรับผิดชอบ และการสร้างแรงบันดาลใจให้ผู้อื่นคือหัวใจที่แท้จริงของการเป็นผู้นำ ค้นพบวิธีคิด วิธีปฏิบัติ และนิสัยของผู้นำไร้ตำแหน่งที่คุณสามารถเริ่มลงมือทำได้ตั้งแต่วินาทีนี้",
        pagesCount = 245,
        isbn = "978-616-XXX-XXX-X",
        language = "ภาษา: ไทย",
        formats = "EPUB & PDF",
        chapters = listOf(
            Chapter(1, "คุณไม่จำเป็นต้องมีตำแหน่งเพื่อเป็นผู้นำ", 18, "เปิดม่านปรัชญาของผู้นำที่แท้จริงที่สร้างการเปลี่ยนแปลงจากจุดที่คุณยืนอยู่"),
            Chapter(2, "ยุคสมัยใหม่และกฎเกณฑ์ใหม่แห่งความสำเร็จ", 22, "ทำไมองค์กรที่ชนะในอนาคตคือองค์กรที่ทุกคนคิดแบบผู้นำ"),
            Chapter(3, "การสนทนาครั้งแรก: การเป็นเลิศในสิ่งที่ทำ", 24, "เทคนิค 5 ประการสู่การปฏิบัติงานระดับ Masterclass"),
            Chapter(4, "การสนทนาที่สอง: ช่วงเวลาอันยากลำบากสร้างผู้นำที่ยิ่งใหญ่", 20, "เปลี่ยนวิกฤตให้เป็นโอกาสในการเติบโต"),
            Chapter(5, "การสนทนาที่สาม: ความสัมพันธ์ที่ลึกซึ้งนำมาซึ่งผลลัพธ์ที่ยิ่งใหญ่", 19, "การสร้างความไว้วางใจและความร่วมมือ")
        ),
        reviews = listOf(
            Review("ธีรภัทร เมธากุล", 5.0f, "12 ก.พ.", "หนังสือเล่มนี้เปลี่ยนวิธีมองบทบาทตัวเองในที่ทำงานไปอย่างสิ้นเชิง ทำให้ตระหนักว่าเราสร้างอิมแพกต์ได้เสมอ"),
            Review("อรัญญา ทวีเกียรติ", 4.8f, "8 ก.พ.", "คำสอนของโรบิน ชาร์มา ยังคงทรงพลังและอ่านเข้าใจง่าย นำไปประยุกต์ใช้ได้ทั้งเรื่องงานและเรื่องส่วนตัว"),
            Review("สมชาย ฤกษ์ดี", 5.0f, "29 ม.ค.", "การแปลทำได้สละสลวยมาก เนื้อหาชวนติดตามและจุดประกายไฟในการทำงาน")
        )
    )

    // Shelf books (Image 8)
    val bookshelfItems = listOf(
        Book(
            id = "shelf-1",
            title = "ปาฏิหาริย์ร้านชำของคุณนามิยะ",
            subtitle = "Miracles of the Namiya General Store",
            author = "ฮิงาชิโนะ เคโงะ",
            publisher = "น้ำพุสำนักพิมพ์",
            price = 285,
            rating = 4.95f,
            reviewsCount = "4.5k",
            category = "นิยายแปล",
            coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCzVU4XAaCP9v2bkzcVKr1lnmdD__xZjwbHsRs9rqxk1zQze7Q456H2tSwVwly9kimNs3A7-WwMA0u3QwmdyIPZI9W8X-uu6vdpQt5Ue8uo6REfkjkdetgiZeM0uaiI8xMWPZEHL3GKzWNyNTpXWx6rGjSM2HLN0-CYVBdgLmEsz6OeNOg9dNUSFKbmeKNjRXLBCcIvajgN8wP_ZgymDhCoPQsNdjUqDikPHSnnejWl",
            synopsis = "ร้านชำเก่าแก่ที่เจ้าของรับตอบจดหมายปรึกษาปัญหาชีวิต ไม่ว่าจะเป็นคำถามแปลกประหลาดหรือจริงจัง ค่ำคืนหนึ่งหัวขโมยสามคนหลบเข้าไปในร้านและพบว่ามีจดหมายจากอดีตหย่อนเข้ามา...",
            pagesCount = 384,
            isbn = "978-616-287-260-0",
            isInShelf = true,
            shelfCategory = "อ่านวันหยุด",
            status = ReadingStatus.FINISHED,
            readProgress = 100,
            lastReadText = "อ่านจบแล้ว",
            isFavorite = true
        ),
        Book(
            id = "shelf-2",
            title = "Sapiens ประวัติย่อมนุษยชาติ",
            subtitle = "A Brief History of Humankind",
            author = "Yuval Noah Harari",
            publisher = "ยิปซี",
            price = 395,
            rating = 4.9f,
            reviewsCount = "6.2k",
            category = "ประวัติศาสตร์",
            coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDBJOtleBq89uOSRfTnyrGpWwm4ExlIxSBNSUUei-_wIyRRsv0822D_rdKvvYSj5YSjrHHzqWDbu6l1Ja12nOL3_zbkamQ_wtq_YnTxx_niVFr2ncPfw-8PyMJJbD9x6bY5ZpqGJ4X8P6twuGsWez1lvDEciR1ydvBNqCgDUOsloh_d0pAl-mWgR1sc0rwNuAfVZtMTRSXnZcuSi0O6i9hbyFwMo9kJwuoftiPYJotK",
            synopsis = "100,000 ปีที่แล้ว เคยมีมนุษย์ถึง 6 สปีชีส์อาศัยอยู่บนโลก แต่เหตุใดวันนี้จึงเหลือเพียง โฮโม เซเปียนส์ เพียงหนึ่งเดียว? การปฏิวัติการรับรู้ การปฏิวัติเกษตรกรรม และการปฏิวัติวิทยาศาสตร์ พาเรามาถึงจุดนี้ได้อย่างไร",
            pagesCount = 608,
            isbn = "978-616-301-656-0",
            isInShelf = true,
            shelfCategory = "คัดสรรสำหรับทำงาน",
            status = ReadingStatus.READING,
            readProgress = 45,
            isAudio = true,
            audioChapterText = "เสียง (ตอนที่ 12)"
        ),
        continueReadingBook,
        Book(
            id = "shelf-4",
            title = "คิดแบบผู้นำไร้ตำแหน่ง",
            subtitle = "Leadership without a Title",
            author = "โรบิน ชาร์มา",
            publisher = "สำนักพิมพ์ Readscape",
            price = 219,
            rating = 4.8f,
            category = "พัฒนาตนเอง",
            coverUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDViH76dnPMalvOWbnzrO52mBPytowujJzF75J6hXd5oqvqQU4OyduKjjSc7pe1Dedi8QdgJ7cySarhysgvR1lPOHjcTHc9mJo4IfWNeyrWFDY5l-HWvZPTDMSCjNAVtGytQagxTUywjbN020--waQOTdhUz80Ku9SRkP3eDG7FR9Gj4HTGx_BVXQNPeMkHFf5QLQ9p-Kr9tU0pdh6CgTlqhKu3SctA19MYESasBRWE",
            synopsis = "ปลดล็อกศักยภาพความเป็นผู้นำในตัวคุณโดยไม่ต้องรอตำแหน่ง",
            pagesCount = 245,
            isbn = "978-616-XXX-XXX-X",
            isInShelf = true,
            shelfCategory = "คัดสรรสำหรับทำงาน",
            status = ReadingStatus.UNREAD,
            readProgress = 0,
            lastReadText = "ยังไม่อ่าน"
        )
    )

    val shelfFolders = listOf(
        ShelfFolder("f-1", "คัดสรรสำหรับทำงาน", 5, 0xFF006C4A), // Green dot
        ShelfFolder("f-2", "อ่านวันหยุด", 8, 0xFFD97706),    // Amber dot
        ShelfFolder("f-3", "พัฒนาตนเอง", 3, 0xFF9E3D17)     // Terracotta dot
    )

    val allCategories = listOf(
        "ทั้งหมด",
        "พัฒนาตนเอง",
        "นิยาย & วรรณกรรม",
        "ธุรกิจ & การเงิน",
        "เทคโนโลยี & AI",
        "จิตวิทยา"
    )

    // Helper to get all available books
    fun getAllCatalog(): List<Book> {
        val list = mutableListOf<Book>()
        list.add(continueReadingBook)
        list.addAll(bestSellers)
        list.addAll(recommendedBooks)
        list.add(detailsFeaturedBook)
        list.addAll(bookshelfItems)
        return list.distinctBy { it.id }
    }
}
